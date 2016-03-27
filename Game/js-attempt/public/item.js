class Item {
  constructor(type, description, status) {
    this.type = type;
    this.description = description;
    this.status = status;
  }

  get type() {
    return this.type;
  }

  get description() {
    return this.description;
  }

  get status() {
    return this.status;
  }

  set type(newType) {
    return this.type = newType;
  }

  set description(newDesc) {
    return this.description = newDesc;
  }

  set status(newStatus) {
    return this.status = newStatus;
  }

}