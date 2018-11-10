(ns robot-name)

(def robomap (java.util.HashMap.))

(defn get-next-key []
   (if (.isEmpty robomap)
      0
      (inc (apply max (.keySet robomap)))))

(defn gen-ch [] (char (+ (int \A) (int (rand 26)))))

(defn gen-num [] (+ 100 (int (rand 899))))

(defn gen-name [] 
   (str (gen-ch) (gen-ch) (gen-num)))

(defn robot [] 
   (let [key (get-next-key)
        name (gen-name)
        zzz (.put robomap key name)]
     key))

(defn robot-name [robot] 
    (.get robomap robot))

(defn reset-name [robot] 
   (.put robomap robot (gen-name)))

