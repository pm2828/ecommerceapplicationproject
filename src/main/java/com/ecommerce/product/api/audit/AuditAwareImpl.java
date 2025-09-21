//package com.ecommerce.product.api.audit;
//
//
//
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component("auditAwareImpl")
//public class AuditAwareImpl implements AuditorAware<String> {
//
//    /**
//     * Returns the current auditor of the application.
//     * You can later replace "PRODUCT_MS" with the logged-in username from Spring Security.
//     *
//     * @return the current auditor
//     */
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        // Hardcoded for now
//        return Optional.of("PRODUCT_MS");
//    }
//}
