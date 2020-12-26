package com.dot.base.utils;

/**
 * @author : xuzhennan
 * @Project : dot-admin
 * @Package : com.dot.admin.util
 * @email : xuzhennan@58.com
 * @date : 2020年12月24日 11:20 上午
 */

import com.dot.base.exception.ValidateException;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

public class CheckUtils {
  private List<Check> checkList = Lists.newArrayList();

  private CheckUtils() {
  }

  public static CheckUtils create() {
    return new CheckUtils();
  }

  public static void checkNull(Object value, String msg) {
    CheckUtils.Check check = new CheckUtils.Check(CheckUtils.CheckType.NULL, value, msg);
    if(check.check()) {
      throw new ValidateException(check.getCode().intValue(), check.getMsg());
    }
  }

  public static void checkNull(Object value, Integer code, String msg) {
    CheckUtils.Check check = new CheckUtils.Check(CheckUtils.CheckType.NULL, value, code, msg);
    if(check.check()) {
      throw new ValidateException(check.getCode().intValue(), check.getMsg());
    }
  }

  public static void checkEmpty(Object value, String msg) {
    CheckUtils.Check check = new CheckUtils.Check(CheckUtils.CheckType.EMPTY, value, msg);
    if(check.check()) {
      throw new ValidateException(check.getCode().intValue(), check.getMsg());
    }
  }

  public static void checkEmpty(Object value, Integer code, String msg) {
    CheckUtils.Check check = new CheckUtils.Check(CheckUtils.CheckType.EMPTY, value, code, msg);
    if(check.check()) {
      throw new ValidateException(check.getCode().intValue(), check.getMsg());
    }
  }

  public static void checkBlank(Object value, String msg) {
    CheckUtils.Check check = new CheckUtils.Check(CheckUtils.CheckType.BLANK, value, msg);
    if(check.check()) {
      throw new ValidateException(check.getCode().intValue(), check.getMsg());
    }
  }

  public static void checkBlank(Object value, Integer code, String msg) {
    CheckUtils.Check check = new CheckUtils.Check(CheckUtils.CheckType.BLANK, value, code, msg);
    if(check.check()) {
      throw new ValidateException(check.getCode().intValue(), check.getMsg());
    }
  }

  public CheckUtils addCheckNull(Object value, String msg) {
    this.checkList.add(new CheckUtils.Check(CheckUtils.CheckType.NULL, value, msg));
    return this;
  }

  public CheckUtils addCheckNull(Object value, Integer code, String msg) {
    this.checkList.add(new CheckUtils.Check(CheckUtils.CheckType.NULL, value, code, msg));
    return this;
  }

  public CheckUtils addCheckEmpty(Object value, String msg) {
    this.checkList.add(new CheckUtils.Check(CheckUtils.CheckType.EMPTY, value, msg));
    return this;
  }

  public CheckUtils addCheckEmpty(Object value, Integer code, String msg) {
    this.checkList.add(new CheckUtils.Check(CheckUtils.CheckType.EMPTY, value, code, msg));
    return this;
  }

  public CheckUtils addCheckBlank(String value, String msg) {
    this.checkList.add(new CheckUtils.Check(CheckUtils.CheckType.BLANK, value, msg));
    return this;
  }

  public CheckUtils addCheckBlank(String value, Integer code, String msg) {
    this.checkList.add(new CheckUtils.Check(CheckUtils.CheckType.BLANK, value, code, msg));
    return this;
  }

  public void check() {
    if(!this.checkList.isEmpty()) {
      Iterator var1 = this.checkList.iterator();

      CheckUtils.Check check;
      do {
        if(!var1.hasNext()) {
          return;
        }

        check = (CheckUtils.Check)var1.next();
      } while(!check.check());

      throw new ValidateException(check.getCode().intValue(), check.getMsg());
    }
  }

  public List<String> checkBatch() {
    List<String> msgList = Lists.newArrayList();
    if(this.checkList.isEmpty()) {
      return msgList;
    } else {
      Iterator var2 = this.checkList.iterator();

      while(var2.hasNext()) {
        CheckUtils.Check check = (CheckUtils.Check)var2.next();
        if(check.check()) {
          msgList.add(check.getMsg());
        }
      }

      return msgList;
    }
  }

  public static class Check {
    private CheckUtils.CheckType type;
    private Object value;
    private Integer code;
    private String msg;

    public Check(CheckUtils.CheckType type, Object value, String msg) {
      this(type, value, Integer.valueOf(600), msg);
    }

    public Check(CheckUtils.CheckType type, Object value, Integer code, String msg) {
      this.type = type;
      this.value = value;
      this.code = code;
      this.msg = msg;
    }

    public Integer getCode() {
      return this.code;
    }

    public String getMsg() {
      return this.msg;
    }

    public boolean check() {
      switch(this.type) {
        case NULL:
          return ObjUtils.isNull(this.value);
        case EMPTY:
          return ObjUtils.isEmpty(this.value);
        case BLANK:
          return ObjUtils.isBlank(this.value);
        default:
          return false;
      }
    }
  }

  public static enum CheckType {
    NULL,
    EMPTY,
    BLANK;
    private CheckType() {
    }
  }
}
