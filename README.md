# گزارش آزمایش - الگوهای Adapter و Strategy

## بخش اول - پیاده‌سازی الگوی Adapter

### زیربخش ۱ - انتخاب نوع Adapter

در این پروژه از **Object Scope Adapter** استفاده کردم. دلایل انتخاب:

- **انعطاف‌پذیری بیشتر**: امکان تغییر رفتار در زمان اجرا
- **استفاده از Composition**: به جای inheritance از composition استفاده می‌کند که coupling کمتری دارد
- **قابلیت تست بهتر**: می‌توان graph object را mock کرد
- **سازگاری با کد موجود**: کلاس‌های فعلی از interface ساده‌تری استفاده می‌کنند

### زیربخش ۲ - نحوه پیاده‌سازی الگو

مراحل پیاده‌سازی:

**1. ایجاد Interface مشترک:**
```java
public interface GraphAdapter {
    Collection<Integer> getNeighbors(Integer vertex);
    void addVertex(Integer vertex);
    void addEdge(String edgeId, Integer source, Integer target);
}
```

**2. پیاده‌سازی برای JUNG:**
کلاس `JUNGGraphAdapter` که `SparseMultigraph` را wrap می‌کند و interface مشترک را پیاده‌سازی می‌کند.

**3. تغییر کلاس‌های Traverser:**
کلاس‌های `DfsGraphTraverser` و `BfsGraphTraverser` به جای `SparseMultigraph` مستقیم، از `GraphAdapter` استفاده می‌کنند.

## بخش دوم - تغییر کتابخانه

### زیربخش ۱ - گزارش چگونگی تغییر کتابخانه

**مراحل انجام شده:**

1. **اضافه کردن JGraphT dependency** به `pom.xml`
2. **ایجاد JGraphTAdapter**: پیاده‌سازی interface با استفاده از `DefaultUndirectedGraph`
3. **تغییر Main.java**: استفاده از `JGraphTAdapter` به جای `JUNGGraphAdapter`

**کد JGraphTAdapter:**
```java
public class JGraphTAdapter implements GraphAdapter {
    private final Graph<Integer, DefaultEdge> graph;
    
    public JGraphTAdapter() {
        this.graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
    }
    
    @Override
    public Collection<Integer> getNeighbors(Integer vertex) {
        return new ArrayList<>(Graphs.neighborListOf(graph, vertex));
    }
    // سایر متدها...
}
```

### زیربخش ۲ - تغییرات ناشی از تغییر کتابخانه

**تغییرات انجام شده:**

**فایل‌های جدید:**
- `GraphAdapter.java` - Interface مشترک
- `JUNGGraphAdapter.java` - Adapter برای JUNG  
- `JGraphTAdapter.java` - Adapter برای JGraphT

**فایل‌های تغییر یافته:**
- `DfsGraphTraverser.java`: فقط نوع parameter در constructor
- `BfsGraphTraverser.java`: فقط نوع parameter در constructor
- `Main.java`: استفاده از GraphAdapter به جای SparseMultigraph مستقیم
- `pom.xml`: اضافه کردن dependency جدید

**فایل‌های بدون تغییر:**
- `Traverser.java`: هیچ تغییری نداشت
- منطق اصلی الگوریتم‌های BFS و DFS کاملاً دست نخورده باقی ماند

**نتیجه تغییر:**
برای جابجایی بین کتابخانه‌ها فقط کافی است یک خط در Main تغییر کند:
```java
// JUNG:
GraphAdapter graphAdapter = new JUNGGraphAdapter();
// JGraphT:  
GraphAdapter graphAdapter = new JGraphTAdapter();
```

**مزایای حاصل:**

1. **جداسازی Dependencies**: کدهای business logic به کتابخانه خاصی وابسته نیستند
2. **انعطاف‌پذیری**: تغییر کتابخانه بدون تأثیر بر منطق اصلی
3. **maintainability بهتر**: هر تغییر در کتابخانه فقط در یک کلاس Adapter اعمال می‌شود
4. **قابلیت توسعه**: می‌توان adapter برای کتابخانه‌های دیگر نیز اضافه کرد

## بخش سوم - تحلیل وجود الگوی Strategy

### ۱. استفاده از این الگو به چه علتی قابل قبول است؟

در این پروژه، برای پیاده‌سازی روش‌های مختلف پیمایش گراف (مانند BFS و DFS)، از الگوی Strategy استفاده شده است. این الگو باعث می‌شود بتوانیم الگوریتم پیمایش را به صورت جداگانه و قابل تعویض پیاده‌سازی کنیم و در صورت نیاز، بدون تغییر در ساختار گراف، روش پیمایش را تغییر دهیم. این موضوع باعث افزایش انعطاف‌پذیری و توسعه‌پذیری کد می‌شود.

### ۲. روش تحقق این الگو به صورت مختصر

در این پروژه، یک interface به نام `Traverser` تعریف شده است که متد `traverse` را مشخص می‌کند. سپس برای هر روش پیمایش (BFS و DFS)، یک کلاس جداگانه (`BfsGraphTraverser` و `DfsGraphTraverser`) پیاده‌سازی شده است که این interface را پیاده می‌کنند. در نتیجه، می‌توانیم در زمان اجرا، هر کدام از این استراتژی‌ها را به راحتی انتخاب و استفاده کنیم.

## نتیجه‌گیری

اعمال الگوی Adapter باعث شد که تغییر کتابخانه گراف از JUNG به JGraphT با حداقل تأثیر بر کدهای موجود انجام شود. این الگو انعطاف‌پذیری و قابلیت نگهداری کد را به طور قابل توجهی افزایش داد و امکان استفاده از کتابخانه‌های مختلف را بدون تغییر در business logic فراهم کرد.
