(ns gigasecond)

(defn from [year month day]  
   (let [date (java.time.LocalDate/of year month day)
         date (.plusDays date (quot 1000000000 (* 3600 24)))] 
      [(.getYear date) (.getMonthValue date) (.getDayOfMonth date)]))
