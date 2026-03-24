package com.sht.mapper;

import com.sht.pojo.Sequence;

public interface SequenceMapper {

  Sequence getSequence(Sequence sequence);

  void updateSequence(Sequence sequence);
}