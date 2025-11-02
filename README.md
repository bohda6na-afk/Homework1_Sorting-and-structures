Варіант: V3, S5
Співвідношення операцій: A:B:C = 5:10:100

Завдання 2

Case 1: ArrayList
Структура:
ArrayList<Person> allStudents
Складність операцій:
Операція 1: O(n) - лінійний пошук по телефону
Операція 2: O(n + k²) - пошук групи + сортування бульбашкою
Операція 3: O(n²) - пошук прізвища + перевірка унікальності груп

Переваги: Простота реалізації, мінімальне використання пам'яті
Недоліки: Повільні операції на великих даних

Case 2: HashMap
Структура:
HashMap<String, Person> phoneToStudent
HashMap<String, ArrayList<Person>> groupToStudents
HashMap<String, ArrayList<String>> surnameToGroups
Складність операцій:
Операція 1: O(1) - прямий доступ, але O(n) для оновлення списків
Операція 2: O(k²) - отримання групи O(1) + сортування бульбашкою
Операція 3: O(1) - прямий доступ до груп за прізвищем

Переваги: Швидкий пошук, оптимальна для операції 3
Недоліки: Більше пам'яті, складність підтримки індексів

Case 3: TreeSet
Структура:
HashMap<String, Person> phoneToStudent
HashMap<String, TreeSet<Person>> groupToStudents
HashMap<String, HashSet<String>> surnameToGroups
Складність операцій:
Операція 1: O(log k) - оновлення TreeSet
Операція 2: O(1) - студенти вже відсортовані в TreeSet
Операція 3: O(1) - HashSet для швидкого доступу

Переваги: Автоматичне сортування, найшвидша операція 2
Недоліки: Найбільше використання пам'яті


Завдання 3
Сортування S5 (за m_group)

Метод 1: Built-in Sort
Алгоритм: Collections.sort()
Складність: O(n log n)

Метод 2: QuickSort з рандомним pivot
Алгоритм: QuickSort з випадковим вибором опорного елемента
Складність: O(n log n) середня, O(n²) найгірша (рідко через рандомний pivot)
Висновок: Built-in sort швидший завдяки оптимізації для частково відсортованих даних. QuickSort з рандомним pivot показує гарні результати, але поступається оптимізованому.

![image.png](attachment:9673c4dc-b17f-413d-963a-7bd7ec0bb015:image.png)

![image.png](attachment:df28e918-d5fd-461b-b46f-e53d51a31fd9:image.png)
