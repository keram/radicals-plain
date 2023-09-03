(ns mla.csv
  (:require
   [goog.labs.format.csv :as gcsv]))

(defn str->csv [str]
  "Produce hash with :header and :rows as vectors of parsed csv data"
  (let [data (gcsv/parse str)
        header (map keyword (first data))
        rows (rest data)]
    {:header header :rows rows}))

(defn csv->hash-list [csv]
  "Converts output of str->csv to list of hash-maps"
  (map #(into {} (map vector (:header csv) %)) (:rows csv)))

(defn parse [str]
  "Given csv passed as string in returns list of hash-maps."
  (csv->hash-list (str->csv str)))

(def sample
  "simplified,traditional,variants,meaning,pyniyn,examples,comment,colloquial
人,人,亻,\"human, person, people\",ren2,今仁休位他,\"Note similarity with 八, which means eight.\",单人旁（亻），人字头（人）
口,口,,\"mouth, opening\",kou3,古可名告知,\"Note similarity with 囗, which always encloses characters, and means enclosure.\",口字旁
土,土,,earth,tu3,在地型城地,\"Note similarity with 士, which has a longer upper stroke and shorter bottom one, and means scholar.\",提土旁")

(def data
  "simplified,traditional,variants,meaning,pyniyn,examples,comment,colloquial
人,人,亻,\"human, person, people\",ren2,今仁休位他,\"Note similarity with 八, which means eight.\",单人旁（亻），人字头（人）
口,口,,\"mouth, opening\",kou3,古可名告知,\"Note similarity with 囗, which always encloses characters, and means enclosure.\",口字旁
土,土,,earth,tu3,在地型城地,\"Note similarity with 士, which has a longer upper stroke and shorter bottom one, and means scholar.\",提土旁
女,女,,\"woman, female\",nv3,好妄始姓安,,女子旁
心,心,忄，⺗,heart,xin1,必忙忘性想,,竖心旁（忄），心字底（心），竖心底（⺗）
手,手,扌，龵,hand,shou3,持掌打抱押,,提手旁（扌），看字头（龵），手字旁（手）
日,日,,\"sun, day\",ri4,白百明时晩,\"Note similarity with 曰, which is broader and lower, and means to say. Also note 白 which means white.\",日字旁
月,月,,\"moon, month\",yue4,有服青朝明,\"This radical is actually two: moon 月 and meat 肉, but in modern Chinese, they look the same in most cases。\",月字旁
木,木,,tree,mu4,板相根本林,\"Note similarity with 禾, which means grain.\",木字旁
氵,氵,水，氺,water,shui3,永泳海洋沙,\"Note similarity with 冫, which means ice.\",水字旁（水），三点水（氵），泰字底水（氺）
火,火,灬,fire,huo3,灯炎焦然炸,,火字旁（火），四点底（灬）
纟,糹,糸,silk,(mi4),纪纸累细绩,,绞丝旁（纟），独立绞丝（糸）
艹,艹,艸,grass,cao3,花英苦草茶,\"Note that when the radical is on top, the traditional variant has four strokes.\",草字头（艹）
讠,訁,言,speech,yan2,说讲识评试,,言字旁（讠）
辶,辶,⻍,walk,(chuo4),迎通道这近,,走之旁（⻌）
钅,釒,金,\"gold, metal\",jin1,银针钱铁钟,,金字旁（钅）
刂,刂,刀,\"knife, sword\",dao1,分切初利刻,\"Note similarity with 力, which means force.\",立刀旁（刂），刀字旁（刀）
宀,宀,,roof,(mian2),守家室字宅,\"Note similarity with 冖, which means cover, and with 亠, which means lid.\",宝盖头
贝,貝,,shell,bei4,财贪货贸员,\"Note similarity with 见, which means to see.\",贝字旁
一,一,,one,yi1,三旦正百天,,横
力,力,,\"power, force\",li4,力加助勉男,\"Note similarity with 刀, which means knife.\",力字旁
又,又,,right hand,you4,反取受左友,,又字旁
犭,犭,犬,dog,(quan3),犯狂狗献猪,\"Note similarity with 大, which means big.\",反犬旁（犭），犬字旁（犬）
禾,禾,,grain,(he),利私季和香,\"Note similarity with 木, which means tree.\",禾木旁
⺮,⺮,竹,bamboo,zhu2,笑第简筷算,,竹子头（竹字头）
虫,虫,,insect,chong2,強独蛇蛋蚊,\"Even though this radical means insect, it's used for many organisms which aren't insects according to our taxonomy.\",虫字旁
阜,阝left,,\"mound, dam\",(fu4),防阻陆院陈,\"Note that there are two radicals which look like this. On the left, it means mound, dam, and on the right, it means city.\",双耳刀（左耳刀）
大,大,,\"big, very\",da4,天尖因奇美,,大字旁（头）
广,广,,house on cliff,guang3,店府度座庭,\"Note similarity with 厂, which means cliff (i.e. without the house).\",广字旁
田,田,,field,tian2,思留略番累,,田字旁
目,目,罒,eye,mu4,眼睛看相省,Note that the horizontal version can also mean net.,目字旁（目），四字头（罒）
石,石,,stone,shi2,砂破碑矿码,,石字旁
衤,衤,衣,clothes,yi1,初被裁裤袜,\"Note similarity with  礻, which means sign, show or spirit.\",衣字旁（衤）
足,足,⻊,foot,zu2,跑跨跟路距,,足字旁
马,馬,,horse,ma3,码驾骂驻妈,,马字旁
页,頁,,leaf,ye4,顺须领预顶,,页字旁
巾,巾,,\"turban, scarf\",(jin1),市布帝帐帽,,巾字旁
米,米,,rice,mi3,类粉迷粗糖,,米字旁
车,車,,\"cart, vehicle\",che1,轮软军较输,,车字旁
八,八,,eight,ba1,公分趴兵共,\"Note similarity with 人, which means human, person.\",八字旁（头）
尸,尸,,corpse,shi1,尺局尾居展,\"Note similarity with 戶, which means door, family.\",尸字头
寸,寸,,\"thumb, inch\",cun4,寺尊对射付,,寸字旁
山,山,,mountain,shan1,岩岛岁崗岔,,山字旁（头）
攵,攵,攴,\"knock, tap\",(pu1),收改攻做政,\"Note similarity with 夊, which means to walk (slowly).\",反文旁（攵），旧反文旁（攴）
彳,彳,,(small) step,(chi2),彼很律微德,\"Note similarity with 亻, which means human, person.\",双人旁
十,十,,ten,shi2,什计古叶早,,十字旁（头）
工,工,,work,gong1,左江红巧功,,工字旁
方,方,,\"square, raft\",fang1,放旅族仿房,,方字旁
门,門,,gate,men2,间闲问闭闻,,门字框
饣,飠,食,\"eat, food\",shi2,饭饿饮馆饱,,食字旁
欠,欠,,\"lack, yawn\",qian4,欢欧欲次歌,,欠字旁
儿,儿,,\"human, legs\",er2,元四光兄充,\"Note similarity with 人, which means human, person, and with 八 which means eight.\",儿座底
冫,冫,,ice,bing1,冬冷冻况净,,两点水
子,子,,\"child, seed\",zi3,字学存孩季,,子字旁
疒,疒,,sickness,(ne4),病痛疗疯痩,,病字旁
隹,隹,,(short-tailed) bird,(zhui1),雀集难雅谁,,隹字旁
斤,斤,,axe,(jin1),所新听近析,,斤字旁
亠,亠,,lid,(tou2),亡交京,\"Note similarity with  宀, which means roof, and 冖 which means cover.\",点横头
王,王,玉,\"jade, king\",\"yu4, wang2\",主弄皇理现,\"This radical is 玉, but when in composition, it looks like 王, king, and is probably more easily remembered like that.\",王字旁
白,白,,white,bai2,的皆皇怕迫,\"Note similarity with 日, which means sun.\",白字旁
立,立,,\"stand, erect\",li4,音意端亲位,,立字旁
羊,羊,⺶，⺷,sheep,yang2,着样洋美鲜,,羊字旁
艮,艮,,stopping,(gen4),很恨恳根眼,,艮字旁
冖,冖,,cover,(mi4),写军农深荣,,秃宝盖
厂,厂,,cliff,(han4),厚原厉厅厕,\"Note similarity with 广, which has ha house on top (the dot).\",厂字旁
皿,皿,,dish,(min3),盆监盟盛盖,,皿字底
礻,礻,示,\"sign, spirit, show\",shi4,社神视祝祥,\"Note similarity with 衤, which means clothes.\",示字旁
穴,穴,,cave,xue4,空突穷究窗,,穴宝盖
走,走,,\"run, walk\",zou3,起超越赶徒,,走字旁
雨,雨,,rain,yu3,雷雪霜需露,,雨字头
囗,囗,,enclosure,(wei2),回国因图团,\"Note similarity with 口, which does not enclose other components and means mouth.\",国字框
小,小,⺌⺍,small,xiao3,少肖尚尖尘,,小字旁（头）
戈,戈,,halberd,(ge1),成式战感我,,戈字旁
几,几,,table,ji1,朵机风凡凤,,几字旁
舌,舌,,tongue,she2,乱适话舍活,,舌字旁
干,干,,dry,gan1,平刊汗旱赶,,干字旁
殳,殳,,weapon,(shu1),段没投般设,,殳字旁
夕,夕,,\"evening, sunset\",xi1,外多夜名岁,,夕字旁
止,止,,stop,zhi3,正此步歪址,,止字旁
牜,牜,牛，⺧,cow,niu2,告物解特件,,牛字旁（头）
皮,皮,,skin,pi2,披彼波破疲,,皮字旁
耳,耳,,ear,er3,取闻职聪联,,耳字旁
辛,辛,,bitter,xin1,辜辟辣辨辩,,辛字旁
阝right,阝right,邑,city,(yi4),那邦部都邮,\"Note that there are two radicals which look like this. On the left, it means mound, dam, and on the right, it means city.\",双耳刀（右耳刀）
酉,酉,,wine,(you3),醉酒醒酸尊,,酉字旁
青,青,,green/blue,qing1,请清情晴猜,,青字旁
鸟,鳥,,bird,niao3,鸦鸣鸭岛鸡,,鸟字旁
弓,弓,,bow,gong1,引张弱第强,,弓字旁
厶,厶,,private,si1,公勾去私云,,私字旁
户,户,戶,\"door, house\",hu4,所房炉护启,\"Note similarity with 尸, which means corpse.\",户字旁
羽,羽,,feather,yu3,习翻翅塌扇,,羽字旁
舟,舟,,boat,chuan2,般船航盘艇,,舟字旁
里,里,,\"village, mile\",li3,野重量理埋,,里字旁
匕,匕,,spoon,(bi3),匙比北呢旨,,匕字旁
夂,夂,,go (slowly),(sui1),各條复备夏,\"Note similarity with 攵, which means to knock, to rap.\",折文旁
见,見,,see,jian4,观规视现觉,\"Note similarity with 贝, which means shell.\",见字旁
卩,卩,,seal,(jie2),卷印却即危,,单耳刀
罒,罒,网,net,wang3,罗罚罢罪罩,Note that the horizontal version can also mean net.,四字头（罒）
士,士,,scholar,shi4,吉壶志声壮,\"Note similarity with 土, which means earth.\",士字旁
勹,勹,,\"embrace, wrap\",(bao1),包勿勾勺勻,,包字头")
