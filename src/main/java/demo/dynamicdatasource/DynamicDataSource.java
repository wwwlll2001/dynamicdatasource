package demo.dynamicdatasource;

import demo.dynamicdatasource.config.DynamicDataSourceProperties;
import demo.dynamicdatasource.util.DataSourceHolder;
import lombok.Getter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

import static java.util.Collections.unmodifiableCollection;


@Component
@Getter
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Collection<DataSource> dataSources;

    public DynamicDataSource(DynamicDataSourceProperties dynamicDataSourceProperties) {
        List<DataSourceProperties> dataSourcePropertiesList = dynamicDataSourceProperties.getDatasources();
        super.setDefaultTargetDataSource(buildDataSource(dataSourcePropertiesList.get(0)));
        super.setTargetDataSources(buildTargetDataSources(dataSourcePropertiesList));
        super.afterPropertiesSet();

        dataSources = unmodifiableCollection(getResolvedDataSources().values());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getContextDataSourceName();
    }

    private DataSource buildDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    private Map<Object, Object> buildTargetDataSources(List<DataSourceProperties> dataSourcePropertiesList) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        for (DataSourceProperties dataSourceProperties : dataSourcePropertiesList) {
            dataSourceMap.put(dataSourceProperties.getName(), buildDataSource(dataSourceProperties));
        }
        return dataSourceMap;
    }
}
