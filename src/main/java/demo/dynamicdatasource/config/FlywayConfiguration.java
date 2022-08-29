package demo.dynamicdatasource.config;

import demo.dynamicdatasource.DynamicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        dynamicDataSource.getDataSources().forEach(dataSource -> {
            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();
        });
    }
}
