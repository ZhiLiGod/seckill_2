package com.seckill2.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import com.seckill2.dao.SeckillMapper;
import com.seckill2.dao.SuccessKilledMapper;
import com.seckill2.dto.ExposerDto;
import com.seckill2.dto.SeckillDto;
import com.seckill2.dto.SeckillExecution;
import com.seckill2.enums.SeckillState;
import com.seckill2.exception.RepeatKillException;
import com.seckill2.exception.SeckillCloseException;
import com.seckill2.exception.SeckillException;
import com.seckill2.model.Seckill;
import com.seckill2.model.SuccessKilled;
import com.seckill2.service.SeckillService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

  private static final String SLAT = "DEFAULT_KEY";

  @Autowired
  private SeckillMapper seckillMapper;

  @Autowired
  private SuccessKilledMapper successKilledMapper;

  @Override
  public Seckill test() {
    return seckillMapper.selectByPrimaryKey(1);
  }

  @Override
  public List<SeckillDto> getSeckills() {
    List<Seckill> seckills = seckillMapper.queryAll(0, Integer.MAX_VALUE);

    if (!CollectionUtils.isEmpty(seckills)) {
      // @formatter:off
      return seckills.stream()
          .map(s -> convertToSeckillDto(s))
          .collect(Collectors.toList());
      // @formatter:on
    }

    return Collections.emptyList();
  }

  @Override
  public SeckillDto getById(Integer seckillId) {
    Seckill seckill = seckillMapper.selectByPrimaryKey(seckillId);

    if (seckill == null) {
      return null;
    }

    return convertToSeckillDto(seckill);
  }

  @Override
  public ExposerDto exportSeckillUrl(Integer seckillId) {
    SeckillDto seckillDto = getById(seckillId);

    if (seckillDto == null) {
      // @formatter:off
      return ExposerDto.builder()
          .exposed(Boolean.FALSE)
          .seckillId(seckillId)
          .build();
      // @formatter:on
    }

    LocalDateTime now = LocalDateTime.now();

    if (now.isBefore(seckillDto.getStartTime()) || now.isAfter(seckillDto.getEndTime())) {
      // @formatter:off
      return ExposerDto.builder()
          .exposed(Boolean.FALSE)
          .seckillId(seckillId)
          .end(seckillDto.getEndTime())
          .start(seckillDto.getStartTime())
          .now(now)
          .build();
      // @formatter:on
    }

    String md5 = generateMd5(seckillId);

    // @formatter:off
    return ExposerDto.builder()
        .exposed(Boolean.TRUE)
        .md5(md5)
        .seckillId(seckillId)
        .build();
    // @formatter:on
  }

  @Override
  @Transactional
  public SeckillExecution executeSeckill(Integer seckillId, Long userPhone, String md5)
      throws SeckillException, RepeatKillException, SeckillCloseException {
    // validate md5
    validateMd5(md5, seckillId);

    try {
      // reduce stock, save purchase record
      Date now = new Date();
      int updateCount = seckillMapper.reduceNumber(seckillId, now);

      if (updateCount <= 0) {
        throw new SeckillCloseException("Seckill closed");
      } else {
        SuccessKilled successKilled = new SuccessKilled();
        successKilled.setSeckillId(Long.valueOf(seckillId));
        successKilled.setUserPhone(userPhone);
        successKilled.setCreateTime(now);
        successKilled.setState(SeckillState.SUCCESS.getState().byteValue());
        int insertCount = successKilledMapper.insertSelective(successKilled);

        if (insertCount <= 0) {
          throw new RepeatKillException("Repeat Seckilled");
        } else {
          // @formatter:off
          return SeckillExecution.builder()
              .seckillId(seckillId)
              .state(SeckillState.SUCCESS.getState())
              .stateInfo(SeckillState.SUCCESS.getText())
              .build();
          // @formatter:on
        }
      }
    } catch (SeckillCloseException sc) {
      throw sc;
    } catch (RepeatKillException rk) {
      throw rk;
    } catch (Exception e) {
      log.error("Error when Seckilling", e);
      throw new SeckillException("Seckill Inner Error" + e.getMessage());
    }
  }

  private SeckillDto convertToSeckillDto(Seckill seckill) {
    SeckillDto dto = new SeckillDto();
    dto.setId(seckill.getSeckillId());
    dto.setName(seckill.getName());
    dto.setStartTime(seckill.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    dto.setEndTime(seckill.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    dto.setCreateTime(seckill.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

    return dto;
  }

  private String generateMd5(Integer seckillId) {
    String base = seckillId + "/" + SLAT;
    return DigestUtils.md5DigestAsHex(base.getBytes());
  }

  private void validateMd5(String md5, @NonNull final Integer seckillId) throws SeckillException {
    if (md5 == null || !md5.equals(generateMd5(seckillId))) {
      throw new SeckillException("Invalid MD5");
    }
  }

}
