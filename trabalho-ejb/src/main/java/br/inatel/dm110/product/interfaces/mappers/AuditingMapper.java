package br.inatel.dm110.product.interfaces.mappers;

import br.inatel.dm110.api.auditing.AuditingTO;
import br.inatel.dm110.product.entities.auditing.Auditing;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface AuditingMapper {
    AuditingMapper INSTANCE = Mappers.getMapper( AuditingMapper.class );

    AuditingTO auditingToAuditingDto(Auditing auditing);

    Auditing auditingDtoToAuditing(AuditingTO auditingTO);
}
