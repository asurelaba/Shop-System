package entities.enums;

import entities.shop.BillingCounter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
}
