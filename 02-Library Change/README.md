# 02-Library Change

این پوشه شامل کدهای پروژه پس از تغییر کتابخانه گراف (از JUNG به JGraphT) است.

## محتویات:
- Interface مشترک: `GraphAdapter`
- پیاده‌سازی Adapter برای JUNG: `JUNGGraphAdapter`
- پیاده‌سازی Adapter برای JGraphT: `JGraphTAdapter`
- کلاس‌های پیمایشگر: `BfsGraphTraverser` و `DfsGraphTraverser`
- سایر کدهای مرتبط با ساختار پروژه

در این نسخه، علاوه بر JUNG، کتابخانه JGraphT نیز پشتیبانی می‌شود و با تغییر Adapter می‌توان بین این دو کتابخانه جابجا شد. 