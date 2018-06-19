package com.test.config.cassandra;
import com.datastax.driver.core.Statement;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.core.GenericTypeResolver;


public class BasicCurdOperations<ET> {

    protected final Mapper<ET> curd;

    public BasicCurdOperations(MappingManager mappingManager)
    {
        this.curd=mappingManager.mapper((Class<ET>) GenericTypeResolver.resolveTypeArgument(getClass(),BasicCurdOperations.class));
        this.curd.setDefaultSaveOptions(Mapper.Option.saveNullFields(Boolean.FALSE));
    }

    protected void save(ET entity,boolean idempotent,Mapper.Option... options)
    {
        Statement query=curd.saveQuery(entity,options);
        query.setIdempotent(idempotent);
        curd.getManager().getSession().execute(query);
    }

    protected void save(ET entity, boolean idempotent) {
        Statement query = curd.saveQuery(entity);
        query.setIdempotent(idempotent);
        curd.getManager().getSession().execute(query);
    }

    protected void delete(ET entity, boolean idempotent, Mapper.Option... options) {
        Statement query = curd.deleteQuery(entity, options);
        query.setIdempotent(idempotent);
        curd.getManager().getSession().execute(query);
    }

    protected void delete(ET entity, boolean idempotent) {
        Statement query = curd.deleteQuery(entity);
        query.setIdempotent(idempotent);
        curd.getManager().getSession().execute(query);
    }






}
