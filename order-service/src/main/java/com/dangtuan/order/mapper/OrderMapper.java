package com.dangtuan.order.mapper;

import com.dangtuan.order.constants.ApplicationConstants;
import com.dangtuan.order.dto.OrderDto;
import com.dangtuan.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  OrderDto mapToOrderDto(final Order order);

  Order mapToOrder(OrderDto orderDto);

  @Mappings(
      @Mapping(target = ApplicationConstants.ID, ignore = true)
  )
  void mapToUpdate(OrderDto orderDto, @MappingTarget Order order);
}
