package com.coderstower.blog.java_collections_hashmap_vs_treemap.put;


import com.coderstower.blog.java_collections_hashmap_vs_treemap.HashMapExecutionPlan;
import com.coderstower.blog.java_collections_hashmap_vs_treemap.TreeMapExecutionPlan;
import com.coderstower.blog.java_collections_hashmap_vs_treemap.Utils;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapVsTreeMapPutTest {

  @Test
  public void put()
          throws RunnerException, IOException {
    Utils.populateToFile();

    Options opt = new OptionsBuilder()
            .include(
                    HashMapVsTreeMapPutTest.class
                            .getSimpleName())
            .detectJvmArgs()
            .build();

    Collection<RunResult> runResults = new Runner(opt)
            .run();

    Result arrayListResult = Utils
            .find("hashMap",
                    runResults);
    Result linkedListResult = Utils
            .find("treeMap",
                    runResults);

    assertThat(linkedListResult.getScore())
            .isLessThan(arrayListResult.getScore());
  }

  @Benchmark
  @Measurement(iterations = Utils.amountIterations, batchSize = 1, time = 1)
  @BenchmarkMode(Mode.SingleShotTime)
  @Fork(1)
  @Warmup(iterations = 10)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public HashMap<Integer, Integer> hashMap(
          HashMapExecutionPlan executionPlan) {
    HashMap<Integer, Integer> hashMap = executionPlan.hashMap;

    hashMap.put(10000002, 10000002);

    return hashMap;
  }

  @Benchmark
  @Measurement(iterations = Utils.amountIterations, batchSize = 1, time = 1)
  @BenchmarkMode(Mode.SingleShotTime)
  @Fork(1)
  @Warmup(iterations = 10)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public TreeMap<Integer, Integer> treeMap(
          TreeMapExecutionPlan executionPlan) {
    TreeMap<Integer, Integer> list = executionPlan.treeMap;

    list.put(10000002, 10000002);

    return list;
  }
}
