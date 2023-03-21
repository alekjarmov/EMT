package mk.finki.ukim.emt.emt.repository;

import mk.finki.ukim.emt.emt.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
