package com.vitshevl.spring.security.springsecuritydefaultexample.configuration.mapper;

import com.vitshevl.spring.security.springsecuritydefaultexample.controllers.dto.DeveloperDto;
import com.vitshevl.spring.security.springsecuritydefaultexample.model.Developer;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

/**
 * Mapper configuration class.
 */

@Component
public class MapperConfiguration implements OrikaMapperFactoryConfigurer {

  @Override
  public void configure(MapperFactory orikaMapperFactory) {
    orikaMapperFactory.classMap(DeveloperDto.class, Developer.class).byDefault().register();
  }
}
