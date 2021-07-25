const faker = require("faker");
let jsonFile = require("jsonfile");
const COUNT = 100;
const START_DATE = "2021-04-24T00:44:56.700Z";
const END_DATE = "2021-07-24T23:44:56.700Z";

var map = new Map();
let list = [];

function init_map() {
  map.set(6, "SUBSCRIBER");
  map.set(7, "TICKETED");
}

function gen_random() {

  var key = faker.random.number({ min: 6, max: 7 });
  var startDate = faker.date.between(START_DATE, END_DATE);
  var randomHour = faker.random.number({ min: 3600000, max: 86400000 })

  return {
    activist_type: map.get(key),
    end_time: faker.date.between(startDate, new Date(startDate.getTime()+randomHour)).toISOString(),
    fee: faker.random.number(150),
    section_id: key,
    start_time: startDate,
    type: faker.random.arrayElement(["ENTER", "EXIT"]),
  };
}

function init_request_params() {
    console.log("Initializing params...")
  for (let i = 1; i <= COUNT; i++) {
    list[i] = gen_random();
  }
  console.log("Initialization is done...")
}

function write_json() {
  jsonFile.writeFile(
    "queries.json",
    list.filter((param) => param !== null),
    {spaces:4}
    
  );
}

init_map();
init_request_params();
write_json();
