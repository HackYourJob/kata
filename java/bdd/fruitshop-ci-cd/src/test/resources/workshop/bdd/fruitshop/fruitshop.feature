Feature: Calculer le prix du panier
  En tant que supermarché
  Je veux calculer le prix d'un panier
  Afin de pouvoir vendre mes produits

  Scenario: Panier avec un sac de riz
    Given le prix du "paquet de riz" est de "2.49"€
    And un panier avec un "paquet de riz"
    When je calcule prix du panier
    Then le montant du panier est de "2.49"€

  Scenario Outline:
    Given le prix du "<productName>" est de "<unitPrice>"€
    And un panier avec un "<productName>"
    When je calcule prix du panier
    Then le montant du panier est de "<totalPrice>"€
    Examples:
      | productName   | unitPrice | totalPrice |
      | paquet de riz | 2.49      | 2.49       |

  Scenario Outline: Panier avec un produit en plusieurs exemplaires
    Given le prix du "<productName>" étant de "<productUnitPrice>" €
    And un panier avec une quantité de "<productQuantity>" "<productName>"
    When je calcule le prix du panier
    Then le total est de "<totalPrice>" €
    Examples:
      | productName   | productUnitPrice | productQuantity | totalPrice |
      | paquet de riz | 2.49             | 2               | 4.98       |
      | brosse a dent | 0.99             | 4               | 3.96       |


  Scenario: Panier avec un produit en plusieurs exemplaires
    Given le prix du "paquet de riz" est de "2.49"€
    And le prix du "brosse a dent" est de "0.99"€
    And une quantité de "3" "paquet de riz"
    And une quantité de "5" "brosse a dent"
    When je calcule prix du panier
    Then le montant du panier est de "12.42"€

  Scenario: Réduction de 20% sur le kilo de pommes
    Given le prix du "pommes" est de "1.99"€
    And une quantité de "2" "pommes"
    And on applique une réduction de "20"% sur les "pommes"
    When je calcule prix du panier
    Then le montant du panier est de "3.18"€

