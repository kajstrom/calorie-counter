(ns user
  (:require [calorie-counter.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [calorie-counter.figwheel :refer [start-fw stop-fw cljs]]
            [calorie-counter.core :refer [start-app]]
            [calorie-counter.db.core]
            [conman.core :as conman]
            [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'calorie-counter.core/repl-server))

(defn stop []
  (mount/stop-except #'calorie-counter.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn restart-db []
  (mount/stop #'calorie-counter.db.core/*db*)
  (mount/start #'calorie-counter.db.core/*db*)
  (binding [*ns* 'calorie-counter.db.core]
    (conman/bind-connection calorie-counter.db.core/*db* "sql/queries.sql")))

(defn reset-db []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url])))


