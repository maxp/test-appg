# Word Freq map-reduce

```clj
clj -M:dev:nrel
```

- (process-datasets)

## Multicore Algorithm

- make initial file queue
- process file queue by spliteers pool (IO bound) to make initial maps {word count}
- process {word count} queue by pool of CPU bound reducers
- final reducer collects results of {word count} pool and do sorting
