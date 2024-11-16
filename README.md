# Информационные системы - Лабораторная работа №1

`Степанов Арсений - P3309 - 368849`

Вариант: `888`

## Задание

Реализовать информационную систему, которая позволяет взаимодействовать
с объектами класса LabWork, описание которого приведено ниже:

```java
public class LabWork {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private String description;
    private Difficulty difficulty;
    private Discipline discipline;
    private Float minimalPoint;
    private int tunedInWorks;
    private Person author;
}

public class Coordinates {
    private Integer x;
    private Float y;
}

public class Discipline {
    private String name;
    private long lectureHours;
    private int practiceHours;
}

public class Person {
    private String name;
    private Color eyeColor;
    private Color hairColor;
    private Location location;
    private java.time.ZonedDateTime birthday;
    private Float weight;
}

public class Location {
    private long x;
    private Double y;
    private String name;
}

public enum Difficulty {
    INSANE,
    HOPELESS,
    TERRIBLE;
}

public enum Color {
    GREEN,
    BLACK,
    BLUE,
    ORANGE,
    WHITE;
}
```

Разработанная система должна удовлетворять следующим требованиям:

- Основное назначение информационной системы - управление объектами, созданными
на основе заданного в варианте класса.
- Необходимо, чтобы с помощью системы можно было выполнить следующие операции с
объектами: создание нового объекта, получение информации об объекте по ИД,
обновление объекта (модификация его атрибутов), удаление объекта. Операции
должны осуществляться в отдельных окнах (интерфейсах) приложения.При получении
информации об объекте класса должна также выводиться информация о связанных
с ним объектах.
- При создании объекта класса необходимо дать пользователю возможность
связать новый объект с объектами вспомогательных классов, которые могут быть
связаны с созданным объектом и уже есть в системе.
- Выполнение операций по управлению объектами должно осуществляться на серверной
части (не на клиенте), изменения должны синхронизироваться с базой данных.
- На главном экране системы должен выводиться список текущих объетов в виде
таблицы (каждый атрибут объекта - отдельная колонка в таблице). При отображении
таблицы должна использоваться пагинация (если все объекты не помещаются
на одном экране).
- Нужно обеспечить возможность фильтровать/сортировать строки таблицы, которые
показывают объекты (по значениям любой из строковых колонок). Фильтрация
элементов должна производиться только по полному совпадению.
- Переход к обновлению (модификации) объекта должен быть возможен из таблицы с
общим списком объектов и из области с визуализацией объекта (при ее реализации).
- При добавлении/удалении/изменении объекта, он должен автоматически
появиться/исчезнуть/измениться в интерфейсах у других пользователей,
авторизованных в системе.
- Если при удалении объекта с ним связан другой объект, связанные объекты
должны удаляться.
- Пользователю системы должен быть предоставлен интерфейс для
авторизации/регистрации нового пользователя. У каждого пользователя должен
быть один пароль. Требования к паролю: пароль должен быть содержать не менее
n символов. В системе предполагается использование следующих видов пользователей
(ролей):незарегистрированные пользователи,обычные пользователи и администраторы.
Если в системе уже создан хотя бы один администратор, зарегистрировать нового администратора можно только при одобрении одним из существующих администраторов
(у администратора должен быть реализован интерфейс со списком заявок
и возможностью их одобрения).
- Редактировать и удалять объекты могут только пользователи, которые их создали,
и администраторы (администраторы могут редактировать и удалять все объекты).
- Зарегистрированные пользователи должны иметь возможность просмотра всех
объектов, но модифицировать (обновлять) могут только принадлежащие им (объект
принадлежит пользователю, если он его создал). Для модификации объекта должно
открываться отдельное диалоговое окно. При вводе некорректных значений в поля
объекта должны появляться информативные сообщения о соответствующих ошибках.

В системе должен быть реализован отдельный пользовательский интерфейс для
выполнения специальных операций над объектами:

- Удалить один (любой) объект, значение поля minimalPoint которого эквивалентно
заданному.
- Рассчитать среднее значение поля tunedInWorks для всех объектов.
- Вернуть массив объектов, значение поля name которых начинается с заданной
подстроки.
- Добавить лабораторную работу в программу дисциплины.
- Добавить в программу указанной дисциплины 10 самых сложных лабораторных работ.

Представленные операции должны быть реализованы в качестве функций БД, которые
необходимо вызывать из уровня бизнес-логики приложения.

Особенности хранения объектов, которые должны быть реализованы в системе:

- Организовать хранение данных об объектах в реляционной СУБД (PostgreSQL).
Каждый объект, с которым работает ИС, должен быть сохранен в базе данных.
- Все требования к полям класса (указанные в виде комментариев
к описанию классов) должны быть выполнены на уровне ORM и БД.
- Для генерации поля id использовать средства базы данных.
- Пароли при хранении хэшировать алгоритмом SHA-384.
- При хранении объектов сохранять информацию о пользователе, который создал
этот объект, а также фиксировать даты и пользователей, которые обновляли и
изменяли объекты. Для хранения информации о пользователях и об изменениях
объектов нужно продумать и реализовать соответствующие таблицы.
- Таблицы БД, не отображающие заданные классы объектов,
должны содержать необходимые связи с другими таблицами и соответствовать 3НФ.
- Для подключения к БД на кафедральном сервере использовать хост pg,
имя базы данных - studs, имя пользователя/пароль совпадают с
таковыми для подключения к серверу.

При создании системы нужно учитывать следующие особенности организации
взаимодействия с пользователем:

- Система должна реагировать на некорректный пользовательский ввод, ограничивая
ввод недопустимых значений и информируя пользователей о причине ошибки.
- Переходы между различными логически обособленными частями системы должны
осуществляться с помощью меню.
- Во всех интерфейсах системы должно быть реализовано отображение информации о
текущем пользователе (кто авторизован) и предоставляться возможность
изменить текущего пользователя.
- В отдельном окне ИС должна осуществляться визуализация объектов коллекции.
При визуализации использовать данные о координатах и размерах объекта.
Объекты от разных пользователей должны быть нарисованы разными цветами.
При нажатии на объект должна выводиться информация об этом объекте.
- При добавлении/удалении/изменении объекта, он должен автоматически
появиться/исчезнуть/измениться на области у всех других клиентов.

При разработке ИС должны учитываться следующие требования:

- В качестве основы для реализации ИС необходимо использовать Spring MVC.
- Для создания уровня хранения необходимо использовать JPA + Hibernate.
- Разные уровни приложения должны быть отделены друг от друга,
разные логические части ИС должны находиться в отдельных компонентах.
