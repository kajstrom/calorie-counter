(ns calorie-counter.il8n
  (:require [taoensso.tempura :as tempura]))

(def calorie-counter-dictionary
  {:en {:missing "Missing translation"
        :create-account "Create account"
        :home-page "Home"
        :about-page "About"
        :registration "Registration"
        :email "Email"
        :password "Password"
        :first_name "First name"
        :last_name "Last name"
        :submit "Submit"
        :registration-success-notification "Your account has been created. Please log in."
        :validation {:required-field "this is a required field"
                     :invalid-email "invalid email"
                     :max-length "maximum length is %s characters"
                      }
            }
   :fi {:missing "Puuttuva käännös"
        :create-account "Luo käyttäjätili"
        :home-page "Etusivu"
        :about-page "Tietoja"
        :registration "Rekisteröinti"
        :email "Sähköposti"
        :password "Salasana"
        :first_name "Etunimi"
        :last_name "Sukunimi"
        :submit "Lähetä"
        :registration-success-notification "Käyttäjätilisi on luotu. Voit kirjautua sisään."
        :validation {:required-field "pakollinen kenttä"
                     :invalid-email "virheellinen sähköpostiosoite"
                     :max-length "maksimipituus on %s merkkiä"
                     }
            }})

(def opts {:dict calorie-counter-dictionary})
(def trs (partial tempura/tr opts [:en :fi]))