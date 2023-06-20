package com.tinnova.vehicleregister.infrastructure.repository.spec;

import com.tinnova.vehicleregister.domain.model.Vehicle;
import com.tinnova.vehicleregister.domain.repository.filter.VehicleFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;

public class VehicleSpecs {

  private VehicleSpecs() {}

  public static Specification<Vehicle> usingFilter(VehicleFilter filter) {
    return ((root, query, builder) -> {
      var predicates = new ArrayList<Predicate>();

      if (Objects.nonNull(filter.getBrand())) {
        predicates.add(builder.equal(root.get("brand"), filter.getBrand()));
      }

      if (Objects.nonNull(filter.getYear())) {
        predicates.add(builder.equal(root.get("year"), filter.getYear()));
      }

      return builder.and(predicates.toArray(new Predicate[0]));
    });
  }
}
