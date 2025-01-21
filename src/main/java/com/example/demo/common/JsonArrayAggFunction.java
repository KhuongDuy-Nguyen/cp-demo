/**
 * Pharmacy Stars, LLC. Copyright (c) 2023‐2024 All Rights Reserved.
 */
package com.example.demo.common;

import com.blazebit.persistence.spi.FunctionRenderContext;
import com.blazebit.persistence.spi.JpqlFunction;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duy.nguyen
 */
@Slf4j
public class JsonArrayAggFunction implements JpqlFunction {

  @Override
  public boolean hasArguments() {
    return false;
  }

  @Override
  public boolean hasParenthesesIfNoArguments() {
    return false;
  }

  @Override
  public Class<?> getReturnType(Class<?> firstArgumentType) {
    return String.class;
  }

  @Override
  public void render(FunctionRenderContext context) {
    if (context.getArgumentsSize() != 1) {
      log.error("Invalid arguments for JSON_ARRAYAGG function");
    }
    context.addChunk("JSON_ARRAYAGG(");
    context.addArgument(0);
    context.addChunk(")");
  }
}
