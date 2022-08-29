package demo.dynamicdatasource.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@ConfigurationProperties(prefix = "multitenant")
public class DynamicDataSourceProperties {

    private List<DataSourceProperties> datasources;
}
