(ns bank-account)

(def accounts (atom {}))

(defn get-next-account-number [] 
   (if (empty? @accounts)
       0
       (inc (apply max (keys @accounts)))))

(defn open-account [] 
   (let [number (get-next-account-number)
         x (swap! accounts assoc number {:status :open :balance 0})]
       number))

(defn close-account [account] 
         (swap! accounts assoc account {:status :closed :balance nil})) 

(defn get-status [account] 
  ((@accounts account) :status)) 

(defn get-balance [account] 
  ((@accounts account) :balance)) 

(defn update-balance [account add-on] 
   (swap! accounts assoc account {:status (get-status account) :balance (+ (get-balance account) add-on)})) 

