package com.example.educationapp.data.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.educationapp.data.model.dao.*;
import com.example.educationapp.data.model.entities.*;

@Database(entities = {Course.class, CourseProgress.class, Direction.class, EducationMaterial.class,
                    InterestTestAnswers.class, InterestTestQuestions.class, InterestTestResult.class,
                    Stage.class, Task.class, TaskOptions.class, User.class, UsersCourses.class, UserTaskResult.class, Achievements.class, UserAchievements.class},
                    version = 7)
public abstract class EducationPlatformDB extends RoomDatabase {
    // Объявление DAO для каждой сущности.
    public abstract DirectionDao directionDao();

    public abstract CourseDao courseDao();
    public abstract CourseProgressDao courseProgressDao();

    public abstract UserDao userDao();

    public abstract StageDao stageDao();

    public abstract EducationMaterialDao educationMaterialDao();

    public abstract TaskDao taskDao();

    public abstract TaskOptionsDao taskOptionsDao();

    public abstract UserTaskResultDao userTaskResultDao();

    public abstract InterestTestQuestionsDao interestTestQuestionsDao();

    public abstract InterestTestAnswersDao interestTestAnswersDao();

    public abstract InterestTestResultDao interestTestResultDao();

    public abstract UsersCoursesDao usersCoursesDao();
    public abstract UserAchievementsDao userAchievementsDao();
    public abstract AchievementsDao achievementsDao();

    // Создание экземпляра базы данных.
    private static volatile EducationPlatformDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static EducationPlatformDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EducationPlatformDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EducationPlatformDB.class, "education_platform_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> InitializeData(INSTANCE)).start();
        }
    };

    public static void InitializeData(EducationPlatformDB db) {
        databaseWriteExecutor.execute(() -> {
            Log.d("init data", "иницировано");
            CourseDao courseDao = db.courseDao();
            CourseProgressDao courseProgressDao = db.courseProgressDao();
            DirectionDao directionDao = db.directionDao();
            EducationMaterialDao educationMaterialDao = db.educationMaterialDao();
            InterestTestAnswersDao interestTestAnswersDao = db.interestTestAnswersDao();
            InterestTestQuestionsDao interestTestQuestionsDao = db.interestTestQuestionsDao();
            InterestTestResultDao interestTestResultDao = db.interestTestResultDao();
            StageDao stageDao = db.stageDao();
            TaskDao taskDao = db.taskDao();
            TaskOptionsDao taskOptionsDao = db.taskOptionsDao();
            UserDao userDao = db.userDao();
            UsersCoursesDao usersCoursesDao = db.usersCoursesDao();
            UserTaskResultDao userTaskResultDao = db.userTaskResultDao();
            AchievementsDao achievementsDao = db.achievementsDao();
            UserAchievementsDao userAchievementsDao = db.userAchievementsDao();

//            interestTestQuestionsDao.insert(new InterestTestQuestions("Склонен к логическим рассуждениям, способен оперировать абстрактными понятиями",1));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Продуктивен, чем бы ни занимался (рисование, сочинение историй, конструирование и др.), способен предложить большое количество самых разных идей и решений",2));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Осваивает новые знания легко и быстро, все «схватывает на лету»",3));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит рисовать, лепить, заниматься художественным конструированием",4));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Проявляет большой интерес к музыкальным занятиям",5));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит сочинять (писать) рассказы или стихи, может легко построить сюжет, начиная от завязки и кончая разрешением какого-либо конфликта",6));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Легко входит в роль какого-либо персонажа (человека или животного и др",7));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Проявляет повышенный интерес к техническим устройствам (машинам и механизмам)",8));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Быстро и легко осваивает компьютер и другие информационные, технические устройства (телефоны, айфоны, планшеты и т.п.)",9));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Инициативен в общении со сверстниками и взрослыми.",10));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Энергичен, производит впечатление ребенка, нуждающегося в большом объеме движений.",11));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Проявляет большой интерес и исключительные способности к классификации и систематизации",1));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Нестандартно мыслит и часто предлагает неожиданные, оригинальные решения",2));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Легко и быстро запоминает услышанное и прочитанное без специального заучивания, не тратит много времени на то, что нужно запомнить",3));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Оригинален в выборе сюжетов собственных рисунков. Обычно изображает много разных предметов, людей, ситуаций",4));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Чутко реагирует на характер и настроение музыки",5));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Рассказывая о чем-то, умеет хорошо придерживаться выбранного сюжета, не теряет основную мысль",6));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит игры-драматизации. Интересуется актерской игрой",7));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Может использовать старые детали для создания новых поделок, игрушек, технических устройств",8));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Интересуется новыми возможностями популярных компьютерных программ (word, exsel и др.)",9));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Хорошо контролирует свои эмоции, у него преобладает позитивное настроение",10));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит участвовать в спортивных играх и состязаниях",11));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Умеет хорошо излагать свои мысли, имеет большой словарный запас",1));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Изобретателен в выборе и использовании различных предметов в играх",2));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Знает много о таких событиях и проблемах, о которых его сверстники обычно не знают",3));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит составлять оригинальные композиции из цветов, рисунков, камней, марок, открыток и т.д.",4));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Хорошо поет",5));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Придерживается только необходимых деталей в рассказах о событиях, все несущественное отбрасывает, оставляет главное, наиболее характерное",6));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Способен менять тональность и выражение голоса, когда изображает другого человека",7));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит разбираться в причинах неисправности механизмов, любит загадочные поломки и вопросы на «поиск»",8));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит играть в компьютерные игры",9));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Сохраняет уверенность в окружении незнакомых людей",10));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Часто выигрывает в разных спортивных играх у сверстников",11));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Хорошо улавливает связь между причиной и следствием, между одним событием и другим",1));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Способен по-разному подойти к решению одной и той же проблемы",2));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Обгоняет своих сверстников по учебе на год или на два, (должен бы учиться в более старшем классе, чем учится сейчас)",3));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит использовать новые материалы для изготовления рисунков, коллажей, в строительстве детских домиков на игровой площадке",4));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Хорошо играет на каком-нибудь музыкальном инструменте",5));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Выбирает в своих рассказах такие слова, которые хорошо передают эмоциональные состояния героев, их настроения, переживания и чувства",6));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Разыгрывая драматическую сцену способен понять и изобразить конфликт",7));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит рисовать чертежи и схемы механизмов",8));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Нравится учиться дистанционно с помощью компьютера",9));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Улавливает причины поступков других людей, мотивы их поведения. Способен понять недосказанное",10));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Бегает быстрее всех (в детском саду), в классе",11));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит решать сложные задачи, требующие умственного усилия",1));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Способен увлечься, уйти «с головой», в интересующее его, занятие",2));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Проявляет ярко выраженную, разностороннюю любознательность",3));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Охотно рисует, лепит, создает художественные композиции (украшения для дома, одежды и т.д.), в свободное время, без побуждения взрослых",4));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("В игру на музыкальном инструменте или в песню вкладывает много энергии и чувств",5));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Умеет передавать в рассказах такие детали, которые важны для понимания события (что обычно не умеют делать его сверстники), и в то же время не упускает основной линии событий, о которых рассказывает",6));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Склонен передавать чувства через мимику, жесты, движения",7));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Читает (любит, когда ему читают) журналы и статьи о создании новых приборов, машин, механизмов",8));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит рисовать с помощью компьютера, заниматься компьютерной графикой",9));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Часто руководит играми и занятиями других детей. Обладает даром убеждения, способен внушать свои идеи другим",10));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Движется легко, грациозно. Имеет хорошую координацию движений",11));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Наблюдателен, любит анализировать события и явления",1));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Способен не только предлагать, но и разрабатывать собственные и чужие идеи",2));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("В свободное время любит самостоятельно читать научно-популярные издания (детские энциклопедии и справочники), делает это с большим интересом, чем читает художественные книги (сказки, рассказы, повести и др.)",3));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит создавать объемные изображения, работать с глиной, пластилином, бумагой и клеем",4));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит слушать музыкальные записи. Стремится пойти на концерт или туда, где можно слушать музыку",5));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Склонен фантазировать, старается добавить, что-то новое и необычное, когда рассказывает о чем-то уже знакомом и известном всем",6));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Стремится вызывать эмоциональные реакции у других людей, когда о чем-то с увлечением рассказывает",7));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит обсуждать научные события, изобретения, часто задумывается об этом",8));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Любит осваивать новые компьютерные программы",9));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Склонен принимать на себя ответственность, выходящую за рамки характерные для его возраста",10));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Предпочитает проводить свободное время в подвижных спортивных играх",11));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Способен долго удерживать в памяти символы, буквы, слова",1));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Не боится новых попыток, стремится всегда проверить новую идею",2));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Имеет широкий круг интересов, задает много вопросов о происхождении и функциях предметов",3));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Может высказать свою собственную оценку произведениям искусства, пытается воспроизвести то, что ему понравилось, в своем собственном рисунке или созданной игрушке, скульптурно изображении",4));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Сочиняет собственные, оригинальные мелодии",5));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Умеет в рассказах изобразить своих героев очень живыми, передает их характер, эмоции, чувства, переживания",6));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("С большой легкостью передает чувства и эмоциональные переживания",7));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Проводит много времени над конструированием и воплощением собственных «проектов» (модели летательных аппаратов, автомобилей, кораблей)",8));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Пытается разрабатывать собственные компьютерные программы",9));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Другие дети предпочитают выбирать его в качестве партнера по играм и занятиям",10));
//            interestTestQuestionsDao.insert(new InterestTestQuestions("Физически сильнее и выносливее сверстников",11));


//            directionDao.insert(new Direction("Интеллектуальная","","@drawable/brain"));
//            directionDao.insert(new Direction("Творческая","","@drawable/tvorchestvo"));
//            directionDao.insert(new Direction("Академическая","","@drawable/academic"));
//            directionDao.insert(new Direction("Художественная","","@drawable/draw"));
//            directionDao.insert(new Direction("Музыкальная","","@drawable/music"));
//            directionDao.insert(new Direction("Литературная","","@drawable/writer"));
//            directionDao.insert(new Direction("Артистическая","","@drawable/artist"));
//            directionDao.insert(new Direction("Техническая","","@drawable/mekanik"));
//            directionDao.insert(new Direction("Информационные технологии","","@drawable/it"));
//            directionDao.insert(new Direction("Cоциальный интеллект","","@drawable/lider"));
//            directionDao.insert(new Direction("Психомоторная","","@drawable/sport"));

//            courseDao.insert(new Course("Логические лабиринты","Книга задач и головоломок для развития логического мышления",1));
//            courseDao.insert(new Course("Юный ученый","Интерактивный учебник с экспериментами и опытами для самостоятельного выполнения",1));
//            courseDao.insert(new Course("Рисуем истории","Рабочая тетрадь с заданиями на сочетание рисунка и творческого письма",2));
//            courseDao.insert(new Course("Импровизация и фантазия","Набор упражнений на развитие творческого мышления через игру",2));
//            courseDao.insert(new Course("Математика вокруг нас","Учебник с заданиями на поиск математических закономерностей в окружающем мире",3));
//            courseDao.insert(new Course("Энциклопедия юного исследователя","Серия книг с интересными фактами о природе, технике и космосе",3));
//            courseDao.insert(new Course("Шаг за шагом","Пошаговые инструкции по созданию рисунков и картин разными материалами",4));
//            courseDao.insert(new Course("Мир в миниатюре","Руководство по созданию маленьких скульптур и объемных поделок",4));
//            courseDao.insert(new Course("Нотная азбука","Пособие для изучения основ нотной грамоты с примерами и упражнениями",5));
//            courseDao.insert(new Course("Мелодии и ритмы","Сборник музыкальных заданий для развития чувства ритма и слуха",5));
//            courseDao.insert(new Course("Книга для чтения и творчества","Сборник рассказов с заданиями на составление своих историй",6));
//            courseDao.insert(new Course("Слова и образы","Упражнения на развитие навыков письма и создания стихов",6));
//            courseDao.insert(new Course("Танцы народов мира","Иллюстрированное пособие по основам различных танцевальных стилей",7));
//            courseDao.insert(new Course("Экспрессия движения","Задания на развитие пластики и выразительности через танец",7));
//            courseDao.insert(new Course("Маленький конструктор","Книга схем и проектов для сборки моделей из подручных материалов",8));
//            courseDao.insert(new Course("Основы механики","Задания на понимание принципов работы простых механизмов",8));
//            courseDao.insert(new Course("Первые шаги в программировании","Руководство по созданию простых программ и игр",9));
//            courseDao.insert(new Course("Цифровое творчество","Учебник по основам графического дизайна и работы с изображениями",9));
//            courseDao.insert(new Course("Искусство общения","Игры и задания на развитие коммуникативных навыков и эмпатии",10));
//            courseDao.insert(new Course("Школа лидера","Упражнения на формирование лидерских качеств и умение работать в команде",10));
//            courseDao.insert(new Course("Спортивные вызовы","Серия заданий на развитие ловкости, силы и выносливости",11));
//            courseDao.insert(new Course("Игры и эстафеты","Подборка командных игр для развития скорости реакции и взаимодействия",11));


//            stageDao.insert(new Stage(17, "Знакомство с программированием", "Основы программирования и его роли в современном мире.", 1, "@drawable/start"));
//            stageDao.insert(new Stage(17, "Первая программа", "Создание первой программы и понимание её структуры.", 2, "@drawable/keyboard"));
//            stageDao.insert(new Stage(17, "Основы языка", "Изучение основных конструкций и синтаксиса языка программирования.", 3, "@drawable/puzzle"));
//            stageDao.insert(new Stage(17, "Управляющие конструкции", "Условные операторы и циклы в программировании.", 4, "@drawable/ifconstr"));
//            stageDao.insert(new Stage(17, "Типы данных и переменные", "Работа с различными типами данных и переменными.", 5, "@drawable/comp"));
//            stageDao.insert(new Stage(17, "Функции и методы", "Определение и использование функций и методов.", 6, "@drawable/batch"));
//            stageDao.insert(new Stage(17, "Коллекции", "Использование массивов и коллекций для хранения данных.", 7, "@drawable/alghorithm"));
//            stageDao.insert(new Stage(17, "Объектно-ориентированное программирование", "Основы ООП и создание классов и объектов.", 8, "@drawable/oop"));
//            stageDao.insert(new Stage(17, "Интерфейсы и наследование", "Принципы наследования и реализация интерфейсов.", 9, "@drawable/version_control"));
//            stageDao.insert(new Stage(17, "Проектная работа", "Разработка собственного проекта с использованием полученных знаний.", 10, "@drawable/arrow"));

//            achievementsDao.insert(new Achievements("Первая задача","Успешно завершите свою первую задачу", "Завершить задачу","@drawable/achievement"));
//            userAchievementsDao.insert(new UserAchievements(1,2));


        });



    }
}