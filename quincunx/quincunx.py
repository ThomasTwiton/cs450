#!/usr/bin/env python3
"""
The *bean machine*, also known as the *Galton Board* or *quincunx*, is a device invented by Sir Francis Galton to demonstrate the central limit theorem, in particular that the normal distribution is approximate to the binomial distribution.
"""

import argparse
import random
import threading


class Board:
    """
    Class Board
    
    Contains multiple bins that collect beans
    Contains multiple levels of pegs
    """

    def __init__(self, bins: int, start: int):
        """Make a new board of the specified size"""
        self._bins = [0] * bins
        self._pegs = start

    def status(self):
        """Print status"""
        sum = 0
        for bin in self._bins:
            sum += bin
            print("|{:^5}".format(bin), end='')
        print("|  " + str(sum))

    def __len__(self):
        """Return the board size"""
        return len(self._bins)

    def __getitem__(self, idx: int):
        """Get number of beans in the specified bin"""
        return self._bins[idx]

    def __setitem__(self, idx: int, new_value: int):
        """Set number of beans in the specified bin"""
        self._bins[idx] = new_value

    @property
    def pegs(self):
        """Return number of levels of pegs"""
        return self._pegs


class Bean(threading.Thread):
    """
    Class Bean

    Data members: board, current position, probability, lock
    """

    def __init__(self, board: object, start: int, prob: float, lock: object):
        """Make a new Bean"""
        threading.Thread.__init__(self, target=self.run)
        self.galton = board
        self.position = start
        self.probability = prob
        self.lock = lock


    def move_left(self):
        """Move a bean left"""
        with self.lock:
            self.galton[self.position] -= 1
            if self.position > 0:
                self.position -= 1
            self.galton[self.position] += 1
            

    def move_right(self):
        """Move a bean right"""
        with self.lock:
            self.galton[self.position] -= 1
            if self.position < (self.galton.__len__() -1):
                self.position += 1
            self.galton[self.position] += 1
            

    def run(self):
        """Run a bean through the pegs"""
        for r in range(self.galton.pegs):
            chance = random.random()
            if chance < self.probability/2:
                self.move_left()
            elif chance < self.probability:
                self.move_right()

def main():
    """Main function"""
    # Parse command-line arguments
    parser = argparse.ArgumentParser(description="Process the arguments.")
    parser.add_argument("--beans", help="Number of beans to run through the board. Default = 1000")
    parser.add_argument("--bins", help="Number of bins the board has. Default = 11")
    parser.add_argument("--start", help="Layers of pins the beans will go through. Default = 5")
    parser.add_argument("--prob", help="Probability the bean will bounce left. Default = 0.5")

    args = parser.parse_args()
    if args.beans == None:
        beans = 1000
    else:
        beans = args.beans
    if args.bins == None:
        bins = 11
    else:
        bins = args.bins
    if args.start == None:
        start = 5
    else:
        start = args.start
    if args.prob == None:
        prob = 0.5
    else:
        prob = args.prob

    print("Start")
    # Create a list of jobs
    bean_jobs = []
    
    # Create a shared lock
    lock = threading.Lock()
    # Create a board
    galton = Board(bins, start)
    middle_bin = galton._bins.__len__()//2
    galton._bins[middle_bin] = beans

    # Create jobs (beans)
    for i in range(beans):
        b = Bean(galton, middle_bin, prob, lock)
        bean_jobs.append(b)

    # Print the board status
    galton.status()

    # Start jobs
    for bean in bean_jobs:
        bean.start()

    # Stop jobs
    for bean in bean_jobs:
        bean.join()

    # Print the board status
    galton.status()

    print("Done")


if __name__ == "__main__":
    main()

