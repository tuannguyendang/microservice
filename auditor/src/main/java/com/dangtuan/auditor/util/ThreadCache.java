package com.dangtuan.auditor.util;

import java.util.HashMap;

public class ThreadCache extends HashMap<String, String> {

  private static final InheritableThreadLocal<ThreadCache> cache = new InheritableThreadLocal<ThreadCache>() {
    public ThreadCache initialValue() {
      return new ThreadCache();
    }
  };

  public static ThreadCache getCache() {
    return cache.get();
  }
}
