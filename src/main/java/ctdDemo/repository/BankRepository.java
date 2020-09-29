package ctdDemo.repository;

import org.springframework.data.repository.CrudRepository;

import ctdDemo.model.Bank;



public interface BankRepository extends CrudRepository<Bank, Long>{

}
