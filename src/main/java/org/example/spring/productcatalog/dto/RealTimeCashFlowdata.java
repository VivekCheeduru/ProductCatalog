package org.example.spring.productcatalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.spring.productcatalog.Models.CashFlow;
import java.util.List;

public class RealTimeCashFlowdata {
    @JsonProperty("cash_flow")
    private List<CashFlow> cashFlows;

    public List<CashFlow> getCashFlows() {
        return cashFlows;
    }

    public void setCashFlows(List<CashFlow> cashFlows) {
        this.cashFlows = cashFlows;
    }
}
