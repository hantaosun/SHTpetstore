package com.sht.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Sequence")
@Data
public class Sequence implements Serializable {
  public Sequence() {
  }

  public Sequence(String name, int nextId) {
    this.name = name;
    this.nextId = nextId;
  }

  private static final long serialVersionUID = 8278780133180137281L;

  private String name;
  private int nextId;
}