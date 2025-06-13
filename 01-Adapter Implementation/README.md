# 01-Adapter Implementation

این پوشه شامل کدهای پیاده‌سازی اولیه الگوی Adapter است (قبل از تغییر کتابخانه گراف).

## محتویات:
- Interface مشترک: `GraphAdapter`
- پیاده‌سازی Adapter برای JUNG: `JUNGGraphAdapter`
- کلاس‌های پیمایشگر: `BfsGraphTraverser` و `DfsGraphTraverser`
- سایر کدهای مرتبط با ساختار اولیه پروژه

در این نسخه، فقط کتابخانه JUNG مورد استفاده قرار گرفته است و ساختار پروژه به گونه‌ای است که وابستگی به کتابخانه از طریق Adapter جدا شده است. 