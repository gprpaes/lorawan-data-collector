# lorawan-data-collector
MQTT => MongoDB Data collector for Lorawan networks

This is a generic data collector for LoRaWan networks data. The goal is to be able to setup one or many MQTT and MongoDB instances, those instances data will be stored in a relational database.

With these instances we can create a Collector. A Collector is Composed of a MQTT and a MongoDB instance and I/O info (which topics to listen and mapping a topic to a mongo collection).

The project is in early stages of development.
