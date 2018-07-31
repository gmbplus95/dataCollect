package com.ifisolution.config;

import ma.glasnost.orika.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBeanConfig {

    @Bean
    public MappingContext getMappingContext(){
        MappingContext mappingContext = new MappingContext.Factory().getContext();
        mappingContext.setProperty("SHOULD_MAP_NULLS","true");
        mappingContext.setProperty("FILTERS"," size = 0");
        mappingContext.setProperty("CAPTURE_FIELD_CONTEXT"," false");
        return mappingContext;
    }
}
