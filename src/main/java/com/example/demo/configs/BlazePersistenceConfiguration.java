package com.example.demo.configs;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.spi.JpqlFunctionGroup;
import com.example.demo.common.JsonArrayAggFunction;
import com.example.demo.common.JsonUnqouteFunction;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlazePersistenceConfiguration {

  private final EntityManagerFactory entityManagerFactory;

  public BlazePersistenceConfiguration(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Bean
  public CriteriaBuilderFactory createCriteriaBuilderFactory() {
    CriteriaBuilderConfiguration config = Criteria.getDefault();
    // optionally, perform dynamic configuration
    JpqlFunctionGroup jsonArrayAgg = new JpqlFunctionGroup("JSON_ARRAYAGG", new JsonArrayAggFunction());
    JpqlFunctionGroup jsonUnqoute = new JpqlFunctionGroup("JSON_UNQUOTE", new JsonUnqouteFunction());
    config.registerFunction(jsonArrayAgg);
    config.registerFunction(jsonUnqoute);
    config.setProperty("com.blazebit.persistence.implicit_group_by_from_select", "false");
    return config.createCriteriaBuilderFactory(entityManagerFactory);
  }
}

