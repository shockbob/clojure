(ns meetup)

(def day-of-week-map {:monday 1 :tuesday 2 :wednesday 3 :thursday 4 :friday 5 
                      :saturday 6 :sunday 7})

(def which-map {:first 0 :second 1 :third 2 :fourth 3 :fifth 4 :last -1})

(defn get-day-of-month [year month day]
   (.getValue (.getDayOfWeek (java.time.LocalDateTime/of year month day 0 0))))

(defn get-teenth [month year day-of-week]
    (let [day-number (day-of-week-map day-of-week)
          first-day (first (filter 
                              (fn [x] (= day-number (get-day-of-month year month x)))
                              (range 13 20))) ]
          [year month first-day]))

(defn get-matching-days [year month day-number]
   (let [length-month  (.lengthOfMonth (java.time.YearMonth/of year month))]
    (vec (filter 
            (fn [d] (= day-number (get-day-of-month year month d))) 
            (range 1 (inc length-month)))))) 

(defn get-meetup [month year day-of-week which]
   (let [day-number (day-of-week-map day-of-week)
         matching-days (get-matching-days year month day-number) 
         index (which-map which)]
     (if (neg? index)
         [year month (last matching-days)]
         [year month (matching-days index)])))
    
   
(defn meetup [month year day-of-week which]
   (if (= :teenth which)
       (get-teenth month year day-of-week)
       (get-meetup month year day-of-week which)))
