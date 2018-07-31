package com.ifisolution.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * SpringConfigurableMapper is a convenience type which provides a 
 * simplification for reuse of a particular Orika mapping configuration in 
 * a given context.
 * 
 * @see ConfigurableMapper
 */
@Component
public class MyCustomMapper extends ConfigurableMapper {

    private ApplicationContext applicationContext;

    private MapperFactory mapperFactory;

    /**
	 * Inject application context.
	 *  
	 * @param applicationContext the application context
	 */
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        addSpringMappers();
        addSpringConverter();
    }

    /**
   	 * Inject factory.
   	 *  
   	 * @param factory the mapper factory
   	 */
    @Override
    protected void configure(MapperFactory factory) {
        super.configure(factory);
        this.mapperFactory = factory;
    }

    /**
   	 * Add spring mappers. 
   	 */
    private void addSpringMappers() {
        @SuppressWarnings("rawtypes")
        final Map<String, Mapper> mappers = applicationContext
                .getBeansOfType(Mapper.class);
        for (final Mapper<?, ?> mapper : mappers.values()) {
            addMapper(mapper);
        }
    }

    /**
   	 * Add mapper.
   	 * 
   	 * @param mapper the mapper
   	 */
    private void addMapper(Mapper<?, ?> mapper) {
        this.mapperFactory.registerMapper(mapper);
    }

    /**
   	 * Add spring converters. 
   	 */
    private void addSpringConverter() {
        @SuppressWarnings("rawtypes")
		final Map<String, Converter> converters = applicationContext
                .getBeansOfType(Converter.class);
        for (final Converter<?, ?> converter : converters.values()) {
            addConverter(converter);
        }
    }

    /**
   	 * Add converter.
   	 * 
   	 * @param the converter
   	 */
    private void addConverter(Converter<?, ?> converter) {
        this.mapperFactory.getConverterFactory().registerConverter(converter);
    }

}