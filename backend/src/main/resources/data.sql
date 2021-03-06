INSERT into quest (quest_description,quest_maxPlayers, quest_name, quest_picture, quest_id)
values ("Kiev. It is one of the most beautiful places in the world. It exites with its picturesque views, ancient buildings and wide roads.
This quest will let you find out more about the hidden magic of this city. Ready, steady, go!",
10,"Magic Kiev","https://i.ytimg.com/vi/RAGrx9_yXSw/maxresdefault.jpg", 1);

insert into task (task_description, task_name, task_picture, task_solution, task_order_number,task_type, task_quest_id)
values ("Find the shortest distance (in m) between:
1. The mother of Kiev
2. The wisdom of Kiev
3. The freedom of Kiev", "Task 1", "http://skyandmethod.com/wp-content/uploads/2014/08/IMG_9518_Toronto_sandm_SM.jpg", "5200", 1,"TEXT", 1),
("Find the shortest route in Kiev. Try it. You will find a QR code somewhere there", "Task 2", "http://v.img.com.ua/b/600x500/d/d9/299b235bcaa4f9fec9cb591444321d9d.jpg", "Done", 2,"QR", 1),
("Find the most popular Kiev souvenir. It is 60 years old", "Task 3", "https://cdn3.iconfinder.com/data/icons/christmas-set-1/512/giftbox_2-512.png", "Cake", 3,"TEXT", 1),
("Annualy 800+ people come to this place, do not sleep and pay 500 UAN to get a T-shirt :)", "Task 4", "https://pp.vk.me/c836326/v836326052/4c68/Vv8D2Mhzt2Y.jpg", "MHW2016", 4,"LOCATION", 1);