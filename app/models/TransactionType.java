package models;

import com.avaje.ebean.annotation.EnumValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Mateusz Brycki on 30/12/2016.
 */
public enum TransactionType {

    @EnumValue("INCOME")
    INCOME,

    @EnumValue("OUTCOME")
    OUTCOME
}
