package com.ulisesbocchio.jasyptspringbootstarter;

import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * <p>JasyptSpringBootAutoConfiguration class.</p>
 *
 * @author Ulises Bocchio
 * @version $Id: $Id
 */
@AutoConfiguration
@Import(EnableEncryptablePropertiesConfiguration.class)
public class JasyptSpringBootAutoConfiguration {
}
