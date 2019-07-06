use std::fs::File;
use std::io::prelude::*;

pub fn start() -> std::io::Result<()> {
    let mut file = File::open("D:\\Data\\rust\\advent_of_code_2017\\input\\day1.txt").unwrap();
    let mut contents = String::new();
    file.read_to_string(&mut contents).unwrap();
    println!("Part 1: {}", sum_duplicate_neighbours(&contents.trim()));
    println!("Part 2: {}", sum_duplicate_halfway(&contents.trim()));
    Ok(())
}

pub fn sum_duplicate_halfway(input: &str) -> u32 {
    let digits: Vec<u32> = input.chars().map(|x| x.to_digit(10).unwrap()).collect();
    let mut sum = 0u32;
    let l = digits.len();
    for i in 0..l {
        if digits[i] == digits[(i+(l/2)) % l] {
            sum += digits[i];
        }
    }
    sum
}

pub fn sum_duplicate_neighbours(input: &str) -> u32 {
    let mut sum = 0u32;
    let mut previous = input.chars().last().unwrap();
    for c in input.chars() {
        if c == previous {
            sum += c.to_digit(10).unwrap();  
        }
        previous = c;
    }
    sum
}

#[cfg(test)]
mod tests {
    use super::*;
    #[test]
    fn examples_pt1() {
        assert_eq!(sum_duplicate_neighbours("1122"), 3u32);
        assert_eq!(sum_duplicate_neighbours("1111"), 4u32);
        assert_eq!(sum_duplicate_neighbours("1234"), 0u32);
        assert_eq!(sum_duplicate_neighbours("91212129"), 9u32);
    }

    #[test]
    fn own_examples_pt1() {
        assert_eq!(sum_duplicate_neighbours("11221"),4 );
        assert_eq!(sum_duplicate_neighbours("61697637962276641366442297247367117738114"),2+6+6+4+2+1+7+1 );
        assert_eq!(sum_duplicate_neighbours("616976379622766413664422972473671177381146"),2+6+6+4+2+1+7+1+6 );
        assert_eq!(sum_duplicate_neighbours("0110"),1 );
    }

    #[test]
    fn examples_pt2() {
        assert_eq!(sum_duplicate_halfway("1212"), 6);
        assert_eq!(sum_duplicate_halfway("1221"), 0);
        assert_eq!(sum_duplicate_halfway("123425"), 4);
        assert_eq!(sum_duplicate_halfway("123123"), 12);
        assert_eq!(sum_duplicate_halfway("12131415"), 4);
    }
}