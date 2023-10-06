---
layout:
  title:
    visible: true
  description:
    visible: true
  tableOfContents:
    visible: true
  outline:
    visible: true
  pagination:
    visible: false
---

# Dependency Inversion Principle (DIP)

**Our classes should depend upon interfaces or abstract classes instead of concrete classes and functions.**

> If the OCP states the goal of OO architecture, the DIP states the primary mechanism"

> These two principles are indeed related and we have applied this pattern before while we were discussing the Open-Closed Principle.

We want our classes to be open to extension, so we have reorganized our dependencies to depend on interfaces instead of concrete classes.
