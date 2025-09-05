package com.nisanth.billingsoftware.config;

import com.nisanth.billingsoftware.entity.*;
import com.nisanth.billingsoftware.io.PaymentDetails;
import com.nisanth.billingsoftware.io.PaymentMethod;
import com.nisanth.billingsoftware.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2) // Run after DataInitializer
public class SampleDataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final OrderEntityRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("🚀 Starting sample data initialization...");
        
        if (categoryRepository.count() > 5) {
            log.info("Sample data already exists, skipping initialization");
            return;
        }

        createSampleCategories();
        createSampleItems();
        createSampleOrders();
        
        log.info("✅ Sample data initialization completed successfully!");
    }

    private void createSampleCategories() {
        log.info("Creating sample categories...");
        
        List<CategoryEntity> categories = List.of(
            CategoryEntity.builder()
                .categoryId("CAT-001")
                .name("Electronics")
                .description("Electronic devices and gadgets")
                .bgColor("#007bff")
                .imgUrl("https://images.unsplash.com/photo-1498049794561-7780e7231661?w=300")
                .build(),
                
            CategoryEntity.builder()
                .categoryId("CAT-002")
                .name("Clothing")
                .description("Fashion and apparel items")
                .bgColor("#28a745")
                .imgUrl("https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=300")
                .build(),
                
            CategoryEntity.builder()
                .categoryId("CAT-003")
                .name("Books")
                .description("Books and educational materials")
                .bgColor("#ffc107")
                .imgUrl("https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=300")
                .build(),
                
            CategoryEntity.builder()
                .categoryId("CAT-004")
                .name("Home & Garden")
                .description("Home improvement and garden supplies")
                .bgColor("#17a2b8")
                .imgUrl("https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=300")
                .build(),
                
            CategoryEntity.builder()
                .categoryId("CAT-005")
                .name("Food & Beverages")
                .description("Food items and beverages")
                .bgColor("#fd7e14")
                .imgUrl("https://images.unsplash.com/photo-1488459716781-31db52582fe9?w=300")
                .build(),
                
            CategoryEntity.builder()
                .categoryId("CAT-006")
                .name("Sports & Fitness")
                .description("Sports equipment and fitness gear")
                .bgColor("#e83e8c")
                .imgUrl("https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=300")
                .build()
        );
        
        categoryRepository.saveAll(categories);
        log.info("✅ Created {} categories", categories.size());
    }

    private void createSampleItems() {
        log.info("Creating sample items...");
        
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<ItemEntity> items = new ArrayList<>();
        
        // Electronics items
        CategoryEntity electronics = categories.stream()
            .filter(c -> "Electronics".equals(c.getName()))
            .findFirst().orElse(null);
            
        if (electronics != null) {
            items.addAll(List.of(
                ItemEntity.builder()
                    .itemId("ITEM-001")
                    .name("Wireless Bluetooth Headphones")
                    .description("High-quality wireless headphones with noise cancellation")
                    .price(new BigDecimal("79.99"))
                    .category(electronics)
                    .imgUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-002")
                    .name("Smartphone")
                    .description("Latest model smartphone with advanced features")
                    .price(new BigDecimal("699.99"))
                    .category(electronics)
                    .imgUrl("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-003")
                    .name("Laptop")
                    .description("High-performance laptop for work and gaming")
                    .price(new BigDecimal("1299.99"))
                    .category(electronics)
                    .imgUrl("https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-004")
                    .name("Wireless Mouse")
                    .description("Ergonomic wireless mouse with precision tracking")
                    .price(new BigDecimal("29.99"))
                    .category(electronics)
                    .imgUrl("https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=300")
                    .build()
            ));
        }
        
        // Clothing items
        CategoryEntity clothing = categories.stream()
            .filter(c -> "Clothing".equals(c.getName()))
            .findFirst().orElse(null);
            
        if (clothing != null) {
            items.addAll(List.of(
                ItemEntity.builder()
                    .itemId("ITEM-005")
                    .name("Cotton T-Shirt")
                    .description("Comfortable 100% cotton t-shirt")
                    .price(new BigDecimal("19.99"))
                    .category(clothing)
                    .imgUrl("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-006")
                    .name("Denim Jeans")
                    .description("Classic blue denim jeans")
                    .price(new BigDecimal("49.99"))
                    .category(clothing)
                    .imgUrl("https://images.unsplash.com/photo-1542272604-787c3835535d?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-007")
                    .name("Winter Jacket")
                    .description("Warm winter jacket with waterproof material")
                    .price(new BigDecimal("129.99"))
                    .category(clothing)
                    .imgUrl("https://images.unsplash.com/photo-1544966503-7cc5ac882d5f?w=300")
                    .build()
            ));
        }
        
        // Books items
        CategoryEntity books = categories.stream()
            .filter(c -> "Books".equals(c.getName()))
            .findFirst().orElse(null);
            
        if (books != null) {
            items.addAll(List.of(
                ItemEntity.builder()
                    .itemId("ITEM-008")
                    .name("Programming Fundamentals")
                    .description("Complete guide to programming basics")
                    .price(new BigDecimal("34.99"))
                    .category(books)
                    .imgUrl("https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-009")
                    .name("Business Strategy")
                    .description("Modern approaches to business strategy")
                    .price(new BigDecimal("42.99"))
                    .category(books)
                    .imgUrl("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-010")
                    .name("Fiction Novel")
                    .description("Bestselling fiction novel")
                    .price(new BigDecimal("14.99"))
                    .category(books)
                    .imgUrl("https://images.unsplash.com/photo-1543002588-bfa74002ed7e?w=300")
                    .build()
            ));
        }
        
        // Home & Garden items
        CategoryEntity homeGarden = categories.stream()
            .filter(c -> "Home & Garden".equals(c.getName()))
            .findFirst().orElse(null);
            
        if (homeGarden != null) {
            items.addAll(List.of(
                ItemEntity.builder()
                    .itemId("ITEM-011")
                    .name("Garden Tools Set")
                    .description("Complete set of essential garden tools")
                    .price(new BigDecimal("89.99"))
                    .category(homeGarden)
                    .imgUrl("https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-012")
                    .name("LED Table Lamp")
                    .description("Modern LED table lamp with adjustable brightness")
                    .price(new BigDecimal("39.99"))
                    .category(homeGarden)
                    .imgUrl("https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=300")
                    .build()
            ));
        }
        
        // Food & Beverages items
        CategoryEntity food = categories.stream()
            .filter(c -> "Food & Beverages".equals(c.getName()))
            .findFirst().orElse(null);
            
        if (food != null) {
            items.addAll(List.of(
                ItemEntity.builder()
                    .itemId("ITEM-013")
                    .name("Organic Coffee Beans")
                    .description("Premium organic coffee beans")
                    .price(new BigDecimal("24.99"))
                    .category(food)
                    .imgUrl("https://images.unsplash.com/photo-1447933601403-0c6688de566e?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-014")
                    .name("Green Tea")
                    .description("Premium green tea leaves")
                    .price(new BigDecimal("18.99"))
                    .category(food)
                    .imgUrl("https://images.unsplash.com/photo-1556881286-fc6915169721?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-015")
                    .name("Honey")
                    .description("Pure natural honey")
                    .price(new BigDecimal("12.99"))
                    .category(food)
                    .imgUrl("https://images.unsplash.com/photo-1558642452-9d2a7deb7f62?w=300")
                    .build()
            ));
        }
        
        // Sports & Fitness items
        CategoryEntity sports = categories.stream()
            .filter(c -> "Sports & Fitness".equals(c.getName()))
            .findFirst().orElse(null);
            
        if (sports != null) {
            items.addAll(List.of(
                ItemEntity.builder()
                    .itemId("ITEM-016")
                    .name("Yoga Mat")
                    .description("Non-slip yoga mat for exercise")
                    .price(new BigDecimal("29.99"))
                    .category(sports)
                    .imgUrl("https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-017")
                    .name("Dumbbells Set")
                    .description("Adjustable dumbbells for strength training")
                    .price(new BigDecimal("149.99"))
                    .category(sports)
                    .imgUrl("https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=300")
                    .build(),
                    
                ItemEntity.builder()
                    .itemId("ITEM-018")
                    .name("Running Shoes")
                    .description("Comfortable running shoes with cushioning")
                    .price(new BigDecimal("89.99"))
                    .category(sports)
                    .imgUrl("https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=300")
                    .build()
            ));
        }
        
        itemRepository.saveAll(items);
        log.info("✅ Created {} items", items.size());
    }

    private void createSampleOrders() {
        log.info("Creating sample orders...");
        
        List<OrderEntity> orders = new ArrayList<>();
        
        // Order 1 - Electronics purchase
        OrderEntity order1 = OrderEntity.builder()
            .orderId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .customerName("John Smith")
            .phoneNumber("+1-555-0123")
            .subtotal(109.98)
            .tax(8.80)
            .grandTotal(118.78)
            .createdAt(LocalDateTime.now().minusDays(5))
            .paymentMethod(PaymentMethod.UPI)
            .paymentDetails(PaymentDetails.builder()
                .razorpayOrderId("order_" + UUID.randomUUID().toString().substring(0, 8))
                .razorpayPaymentId("pay_" + UUID.randomUUID().toString().substring(0, 8))
                .status(PaymentDetails.PaymentStatus.COMPLETED)
                .build())
            .items(List.of(
                OrderItemEntity.builder()
                    .itemId("ITEM-001")
                    .name("Wireless Bluetooth Headphones")
                    .quantity(1)
                    .price(79.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-004")
                    .name("Wireless Mouse")
                    .quantity(1)
                    .price(29.99)
                    .build()
            ))
            .build();
        orders.add(order1);
        
        // Order 2 - Clothing purchase
        OrderEntity order2 = OrderEntity.builder()
            .orderId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .customerName("Emma Johnson")
            .phoneNumber("+1-555-0456")
            .subtotal(199.97)
            .tax(16.00)
            .grandTotal(215.97)
            .createdAt(LocalDateTime.now().minusDays(3))
            .paymentMethod(PaymentMethod.CASH)
            .items(List.of(
                OrderItemEntity.builder()
                    .itemId("ITEM-005")
                    .name("Cotton T-Shirt")
                    .quantity(2)
                    .price(39.98)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-006")
                    .name("Denim Jeans")
                    .quantity(1)
                    .price(49.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-007")
                    .name("Winter Jacket")
                    .quantity(1)
                    .price(129.99)
                    .build()
            ))
            .build();
        orders.add(order2);
        
        // Order 3 - Books and food
        OrderEntity order3 = OrderEntity.builder()
            .orderId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .customerName("Michael Brown")
            .phoneNumber("+1-555-0789")
            .subtotal(92.97)
            .tax(7.44)
            .grandTotal(100.41)
            .createdAt(LocalDateTime.now().minusDays(1))
            .paymentMethod(PaymentMethod.UPI)
            .paymentDetails(PaymentDetails.builder()
                .razorpayOrderId("order_" + UUID.randomUUID().toString().substring(0, 8))
                .razorpayPaymentId("pay_" + UUID.randomUUID().toString().substring(0, 8))
                .status(PaymentDetails.PaymentStatus.COMPLETED)
                .build())
            .items(List.of(
                OrderItemEntity.builder()
                    .itemId("ITEM-008")
                    .name("Programming Fundamentals")
                    .quantity(1)
                    .price(34.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-009")
                    .name("Business Strategy")
                    .quantity(1)
                    .price(42.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-010")
                    .name("Fiction Novel")
                    .quantity(1)
                    .price(14.99)
                    .build()
            ))
            .build();
        orders.add(order3);
        
        // Order 4 - Fitness equipment
        OrderEntity order4 = OrderEntity.builder()
            .orderId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .customerName("Sarah Davis")
            .phoneNumber("+1-555-0147")
            .subtotal(269.97)
            .tax(21.60)
            .grandTotal(291.57)
            .createdAt(LocalDateTime.now().minusHours(6))
            .paymentMethod(PaymentMethod.UPI)
            .paymentDetails(PaymentDetails.builder()
                .razorpayOrderId("order_" + UUID.randomUUID().toString().substring(0, 8))
                .razorpayPaymentId("pay_" + UUID.randomUUID().toString().substring(0, 8))
                .status(PaymentDetails.PaymentStatus.COMPLETED)
                .build())
            .items(List.of(
                OrderItemEntity.builder()
                    .itemId("ITEM-016")
                    .name("Yoga Mat")
                    .quantity(1)
                    .price(29.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-017")
                    .name("Dumbbells Set")
                    .quantity(1)
                    .price(149.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-018")
                    .name("Running Shoes")
                    .quantity(1)
                    .price(89.99)
                    .build()
            ))
            .build();
        orders.add(order4);
        
        // Order 5 - Mixed items
        OrderEntity order5 = OrderEntity.builder()
            .orderId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .customerName("David Wilson")
            .phoneNumber("+1-555-0258")
            .subtotal(87.97)
            .tax(7.04)
            .grandTotal(95.01)
            .createdAt(LocalDateTime.now().minusHours(2))
            .paymentMethod(PaymentMethod.CASH)
            .items(List.of(
                OrderItemEntity.builder()
                    .itemId("ITEM-012")
                    .name("LED Table Lamp")
                    .quantity(1)
                    .price(39.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-013")
                    .name("Organic Coffee Beans")
                    .quantity(1)
                    .price(24.99)
                    .build(),
                OrderItemEntity.builder()
                    .itemId("ITEM-014")
                    .name("Green Tea")
                    .quantity(1)
                    .price(18.99)
                    .build()
            ))
            .build();
        orders.add(order5);
        
        orderRepository.saveAll(orders);
        log.info("✅ Created {} sample orders", orders.size());
    }
}
