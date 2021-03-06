(ns calorie-counter.core
  (:require [reagent.core :as r]
            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType]
            [markdown.core :refer [md->html]]
            [calorie-counter.ajax :refer [load-interceptors!]]
            [ajax.core :refer [GET POST]]
            [secretary.core :as secretary :include-macros true]
            [calorie-counter.session :refer [session]]
            [calorie-counter.components.auth :refer [registration-form login-form]]
            [calorie-counter.il8n :refer [trs]]
            [calorie-counter.components.notification :refer [notification-list]])
  (:import goog.History))

(defn nav-link [uri title page]
  [:li.nav-item
   {:class (when (= page (:page @session)) "active")}
   [:a.nav-link {:href uri} title]])

(defn navbar []
  [:nav.navbar.navbar-dark.bg-primary.navbar-expand-md
   {:role "navigation"}
   [:button.navbar-toggler.hidden-sm-up
    {:type "button"
     :data-toggle "collapse"
     :data-target "#collapsing-navbar"}
    [:span.navbar-toggler-icon]]
   [:a.navbar-brand {:href "#/"} "calorie-counter"]
   [:div#collapsing-navbar.collapse.navbar-collapse
    [:ul.nav.navbar-nav.mr-auto
     [nav-link "#/" (trs [:home-page]) :home]
     [nav-link "#/about" (trs [:about-page]) :about]]]])

(defn about-page []
  [:div.container
   [:div.row
    [:div.col-md-12
     [:img {:src "/img/warning_clojure.png"}]]]])

(defn home-page []
  [:div.container
   [notification-list]
   [:a {:href "#/registration"} (trs [:create-account])]
   [login-form]])

(defn registration-page []
  [:div.container
   [:h1 (trs [:registration])]
   [registration-form]])

(def pages
  {:home #'home-page
   :about #'about-page
   :registration #'registration-page})

(defn page []
  [(pages (:page @session))])

;; -------------------------
;; Routes

(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (swap! session assoc :page :home))

(secretary/defroute "/registration" []
                    (swap! session assoc :page :registration))

(secretary/defroute "/about" []
  (swap! session assoc :page :about))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
        (events/listen
          HistoryEventType/NAVIGATE
          (fn [event]
            (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

(defn mount-components []
  (r/render [#'navbar] (.getElementById js/document "navbar"))
  (r/render [#'page] (.getElementById js/document "app")))

(defn init! []
  (load-interceptors!)
  (hook-browser-navigation!)
  (mount-components))
