package entities.enums;

import entities.shop.BillingCounter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum CounterStatus {
    OPEN,
    CLOSED,
    SERVING_CUSTOMER,
    CALL_MANAGER,
    FREE;

    public List<BillingCounter> getCounters(Collection<BillingCounter> billingCounters) {
        List<BillingCounter> filteredCounters = new ArrayList<>();
        billingCounters.forEach((BillingCounter counter) -> {
            if (counter.getCounterStatus() == this) {
                filteredCounters.add(counter);
            }
        });
        return filteredCounters;
    }

    public static Map<CounterStatus,List<BillingCounter>> groupBy (Collection<BillingCounter> billingCounters){
        return billingCounters
                .stream()
                .collect(
                        Collectors.groupingBy(BillingCounter::getCounterStatus));

    }
}
