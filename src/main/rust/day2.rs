use std::fs::File;
use std::io::prelude::*;

pub fn start() {
    let mut file = File::open("D:\\Data\\rust\\advent_of_code_2017\\input\\day2.txt").unwrap();
    let mut contents = String::new();
    file.read_to_string(&mut contents).unwrap();
    let lines: Vec<&str> = contents.split("\n").collect();
    let res: u32 = lines.iter().map(|l| calc_checksum(l)).sum();
    println!("Part 1: {}", res);
    let res: u32 = lines.iter().map(|l| evenly_divisible(l)).sum();
    println!("Part 2: {}", res);
}

pub fn calc_checksum(line: &str) -> u32 {
    let vals: Vec<u32> = line.split_whitespace().map(|x| x.parse().unwrap()).collect();
    vals.iter().max().unwrap() - vals.iter().min().unwrap()
}

pub fn evenly_divisible(line: &str) -> u32 {
    let vals: Vec<u32> = line.split_whitespace().map(|x| x.parse().unwrap()).collect();
    for &x in vals.iter() {
        for &y in vals.iter() {
            if x == y {
                continue
            }
            if x % y == 0 {
                return x / y;
            }
            if y % x == 0 {
                return y / x;
            }
        }
    }
    0
}


#[cfg(test)]
mod tests {
    use super::*;
   #[test]
   fn examples_pt1() {
       assert_eq!(calc_checksum("5 1 9 5"), 8);
       assert_eq!(calc_checksum("7 5 3"), 4);
       assert_eq!(calc_checksum("2 4 6 8"), 6 );
   }

   #[test]
   fn examples_pt2() {
       assert_eq!(evenly_divisible("5 9 2 8"), 4);
       assert_eq!(evenly_divisible("9 4 7 3"), 3);
       assert_eq!(evenly_divisible("3 8 6 5"), 2);
   }
}