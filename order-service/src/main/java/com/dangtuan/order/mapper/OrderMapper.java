package com.dangtuan.order.mapper;

import com.dangtuan.dto.order.OrderDto;
import com.dangtuan.order.constants.ApplicationConstants;
import com.dangtuan.order.dto.response.OrderResponse;
import com.dangtuan.order.entity.Order;
import java.util.List;
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

  @Mappings({
      @Mapping(target = ApplicationConstants.ID, ignore = true),
      @Mapping(target = ApplicationConstants.TENANT_ID, ignore = true)
  })
  Order mapToOrder(OrderDto orderDto);

  @Mappings({
      @Mapping(target = ApplicationConstants.ID, ignore = true),
      @Mapping(target = ApplicationConstants.TENANT_ID, ignore = true)
  })
  void mapToUpdate(OrderDto orderDto, @MappingTarget Order order);

  List<OrderResponse> toListDto(final List<Order> order);
}
