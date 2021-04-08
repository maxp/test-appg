(ns wordfreq
  (:require
    [clojure.string :as str]
    [faker.generate :as gen]))

(defn string-split [s]
  (str/split s #"\s"))

(defn- n-words-string [n]
  (str/join
    " " (gen/words {:lang :en :n n})))

(defn freqs [acc words]
  (->> words
       (reduce
        (fn [a w]
          (update a w (fnil inc 0)))
        acc)))

(defn n-freqs [n fqs]
  (->> fqs
       (sort-by (comp - second))
       (take n)))

(defn process-datasets [data-sets]
  (->> data-sets
       (reduce
          (fn [a s]
            (freqs a (string-split s)))
          {})
      (n-freqs 4)))

(comment
  (declare DATA_SETS)
  (process-datasets DATA_SETS)
  ;; => (["expert" 6] ["damage" 5] ["science" 4] ["sister" 4])


  ;; generate
  (let [NRECS 20
        NWORDS 19]
    (for [_ (range NRECS)]
      (n-words-string NWORDS)))
  )

(def DATA_SETS 
  ["price law disgust print thought summer education care payment mark story grain weight number law attention cause plant mass"
   "science experience stitch food attempt expert damage protest act point event feeling attention earth substance hearing disease sister increase"
   "voice man light change smell minute substance competition man cry kiss chance sugar breath touch experience death question sea"
   "discovery thing polish science owner force fold coal sand year point sister gold selection blow science polish ray offer"
   "mark building person support disease humor snow bite page twist rain stage wine expert cork growth damage point authority"
   "time run plant steel punishment part harmony representative position mark request owner paste disgust chalk operation way father smash"
   "place ink cork print point poison linen cork meeting mist week road story copper cry sneeze language flight credit"
   "drink daughter slip porter wax mother lift country comfort slip test building wind fall love paper desire country expert"
   "prose fear grass night profit paste damage woman hour surprise advertisement insurance law credit soup danger driving wood fruit"
   "wound sister cloth expert expert burst grain note feeling sky smoke push heat history waste laugh ice credit exchange"
   "print rain mass fold opinion plant experience death month protest birth opinion salt profit behavior damage sky sense friend"
   "water air money print organisation twist cloth kiss porter comfort color building doubt distance sand paste need crime reason"
   "representative amusement sugar sense day family sneeze death road step doubt attack copper purpose part summer instrument morning rice"
   "competition look prose substance adjustment turn value wax damage powder condition month view gold work relation order burst dust"
   "organisation stop room plant industry fiction hate hearing division representative error profit journey distribution chance crack waste news society"
   "jelly owner wash note event snow act cook government week rhythm talk copper thought motion canvas smell seat quality"
   "limit rate suggestion surprise daughter expert building payment flower effect payment walk jelly example position play soap kiss science"
   "sister hope rate teaching move self night place jelly voice person rice coal distance secretary ornament thunder account sex"
   "protest day wax grain cause smoke authority stop scale force weather laugh theory relation servant shock desire secretary shock"
   "invention talk rub reaction industry apparatus note motion group blow event run family harbor help slip steel learning writing"
  ])
 
