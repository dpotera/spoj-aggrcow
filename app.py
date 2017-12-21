import itertools


def main():
    t = int(input())
    results = []
    for i in range(0, t):
        (n, c) = [int(n) for n in input().split(' ')]
        points = [int(input()) for _ in itertools.repeat(None, n)]
        points.sort()
        results.insert(i, get_maximum_dist(points, c))
    for result in results:
        print(result)


def get_maximum_dist(array, cows):
    (start, end) = (1, array[len(array) - 1])
    while start < end-1:
        middle = int((start + end) / 2)
        if check_dist(array, cows, middle):
            start = middle
        else:
            end = middle - 1
    return end if check_dist(array, cows, end) else start


def check_dist(array, cows, distance):
    last_point = array[0]
    cows -= 1
    for i in range(1, len(array)):
        if array[i] - last_point >= distance:
            last_point = array[i]
            cows -= 1
            if cows == 0:
                return True
    return False


if __name__ == '__main__':
    main()
