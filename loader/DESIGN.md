# Loader — Internal Design

Replaces `ShiggyCord/src/lib/api/native/loader.ts` and the `entry.ts` deferred-call mechanism.

## What it does NOT do
- No `globalThis.__SHIGGY_LOADER__`.
- No `isShiggyLoader()`, `getLoaderIdentity()`, `polyfillVendettaLoaderIdentity()`.
- No `getLoaderName()`, `getLoaderVersion()` for cross-client matching.
- No theme/font feature polyfill (`fontPatch === 2`, `storedTheme`, `hasThemeSupport`).

## What it does
- Defines its own runtime identity (internal only, not exposed to external clients).
- Loads the internal bundle directly without deferring for `AppRegistry` / `__fbBatchedBridge` through Metro internals.
- Uses LSPatch-embedded loader injection (same mechanism as `org.lsposed.patch.LSPatch` in manager).

## Evidence basis
- `entry.ts` deferred patches (`onceIndexRequired`, `deferMethodExecution`) rely on `window.__fbBatchedBridge` and `window.RN$AppRegistry`. Vector/LSPatch embeds the runtime before these are initialized, avoiding deferred patch complexity.
- `Shiggy.bundle` contains `shiggyLoaderIdentity` checks (`loaderName`, `loaderVersion`, `fontPatch`, `storedTheme`). Veqq loader removes all of these.

## Implementation direction
Loader is initialized by the patched APK's embedded loader (from LSPatch), not by runtime injection. The loader references an internal config file (not `loader.json` referencing `pyoncord/` or `vendetta_loader.json`).
